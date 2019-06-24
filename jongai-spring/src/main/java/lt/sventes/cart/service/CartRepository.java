package lt.sventes.cart.service;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.sventes.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findCartByCartCode(String cartCode);
}
