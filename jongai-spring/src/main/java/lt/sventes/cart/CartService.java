package lt.sventes.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lt.sventes.products.Product;
import lt.sventes.products.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	// Krepšelio nuskaitymas
	@Transactional(readOnly = true)
	public CartData getCart(String username) {
		// paimti duomenys
		Cart cartFromDB = cartRepository.findCartByUsername(username);
		// atiduodami duomenys
		CartData cartToController = new CartData(cartFromDB.getUsername(), cartFromDB.getProducts());

		return cartToController;
	}


	// krepšelio papildymas (naujos prekės įdėjimas)
	@Transactional
	public void addProductToCart(String user, long productId, String productTitle, int quantity) {
		List<Product> productAlreadyInCart = new ArrayList<Product>();
		Cart currentCart = new Cart(user);
		// Tik jeigu egzistuoja paduotas produktas, vykdome toliau
		if (productRepository.findProductById(productId) != null) {
			Product productToCart = productRepository.findProductById(productId);

			if (cartRepository.existsByUsername(user)) {
				currentCart = cartRepository.findCartByUsername(user);
			}

			productAlreadyInCart = currentCart.getProducts();
			if (productAlreadyInCart == null) {
				productToCart.setQuantity(quantity);
				currentCart.addProduct(productToCart);
			} else {
				int position = -1;
				for (int i = 0; i < productAlreadyInCart.size(); i++) {
					if (productAlreadyInCart.get(i).getId() == productId) {
						position = i;
					}
				}
				if (position == -1) {
					System.out.println("-----------------Tokios prekės nėra krepšelyje!");
					productToCart.setQuantity(quantity);
					currentCart.addProduct(productToCart);
				}

			}

			cartRepository.save(currentCart);
		}
	}

	

	// produkto šalinimas iš krepšelio
	public void deleteProductFromCart(String user, long id) {
		// Cart currentCart = new Cart();
		if (cartRepository.existsByUsername(user)) {
			Cart currentCart = cartRepository.findCartByUsername(user);
			int position = -1;
			if (currentCart.getProducts().size() != 0) {
				for (int i = 0; i < currentCart.getProducts().size(); i++) {
					if (currentCart.getProducts().get(i).getId() == id) {
						position = i;
						break;
					}
				}
				if (position != -1) {
					currentCart.getProducts().remove(position);
					cartRepository.save(currentCart);
				}
			}
		}
	}
}
