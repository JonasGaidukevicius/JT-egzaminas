package lt.sventes.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	
	// Visų produktų gavimas
	@Transactional(readOnly = true)
	public List<ProductData> getProducts() {
		
		return productRepository.findAll().stream().map((product) ->
			new ProductData(product.getId(),
							product.getTitle(),
							product.getPrice(),
							product.getQuantity(),
							product.getProductDetails()
							)).collect(Collectors.toList());	
	}
	
	

	// Vieno produkto gavimas
	@Transactional(readOnly = true)
	public ProductData getProductById(long id) {
		//System.out.println("------------------ koks čia produktas? -> " + productRepository.findProductById(id).toString());
		Product currentProduct = productRepository.findProductById(id);
		ProductData productToController = new ProductData(currentProduct.getId(), currentProduct.getTitle(), currentProduct.getPrice(),
															currentProduct.getQuantity(), currentProduct.getProductDetails());
		return productToController;
	}

	//Naujo produktpo sukūrimas
	@Transactional
	public void createProduct(String title, double price, int quantity, String image, String description) {
		Product newProduct = new Product(title, new ProductDetails(image, description), price, quantity);
		productRepository.save(newProduct);
	}

	//Esamo produkto ištrynimas (metodas aprašytas Repositorijoje)
	@Transactional
	public void deleteProduct(long id) {
		productRepository.deleteProductById(id);
	}

	//Esamo produkto duomenų pakeitimas
	@Transactional
	public void updateProduct(long id, String title, double price, int quantity, String image, String description) {
		Product productToUpdate = productRepository.findProductById(id);
		productToUpdate.setTitle(title);
		productToUpdate.setPrice(price);
		productToUpdate.setQuantity(quantity);
		productToUpdate.setProductDetails(new ProductDetails(image, description));
		productRepository.save(productToUpdate);
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
