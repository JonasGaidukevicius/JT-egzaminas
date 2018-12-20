package lt.sventes.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findProductById(long id);
	void deleteProductById(long id);
	
	//yra paprasti built-in metodai:
	// findById (long id)
	// deleteById (long id)
	
} 
