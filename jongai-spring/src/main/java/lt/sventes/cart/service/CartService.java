package lt.sventes.cart.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import lt.sventes.cart.model.Cart;
import lt.sventes.cart.model.CartData;
import lt.sventes.cart.model.CartStatus;
import lt.sventes.security.payload.ApiResponse;
import lt.sventes.users.models.User;
import lt.sventes.users.service.UserRepository;

@Service
@Slf4j
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;

	// visų krepšelių nuskaitymas
	@Transactional(readOnly = true)
	public List<CartData> getFullListOfCarts(String username) {

		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Gautas username yra: " + username);

		List<CartData> test = cartRepository.findAll().stream()
				.filter(userCart -> userCart.getUser().getUsername().equals(username))
				.map((cart) -> 
				new CartData(cart.getCartCode(),
						cart.getDescription(), cart.getStatus()
				)).collect(Collectors.toList());
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Gautas listas yra: " + test);
		return test;

		/*
		 * return cartRepository.findAll().stream() .filter(userCart ->
		 * userCart.getUser().getUsername().equals("jonas")) .map((cart) -> new
		 * CartData(cart.getCartCode(), cart.getDescription(), cart.getStatus()
		 * )).collect(Collectors.toList());
		 */
	}

	@Transactional
	public ResponseEntity<?> createCart(String username, String description) {
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Gautas vartotoo vardas yra: " + username);

		// 1.Sugeneruoju atsitiktinę eilutę iš 7 simbolių
		String cartCode = RandomStringUtils.random(7, true, true);
		// 2.Modifikuoju description, kad jame nebūtų tarpų
		String modifiedDescription = description.replaceAll("\\s+", "");
		// 3.Sugeneruoju galutinį lauką code
		cartCode += "_" + modifiedDescription;
		User currentUser = userRepository.findUserByUsername(username);

		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Gautas vartotojas yra: " + currentUser);

		Cart newCart = new Cart(cartCode, description, CartStatus.PREPARED, currentUser);
		Cart result = cartRepository.save(newCart);
		log.info("A new cart (" + description + ") has been created");
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/carts/{cartCode}")
				.buildAndExpand(result.getCartCode()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Cart created successfully"));
	}
}
