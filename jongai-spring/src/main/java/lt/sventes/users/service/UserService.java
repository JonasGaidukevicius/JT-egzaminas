//Cia pradejau daryti, bet paskui radau, kad naujas vartotojas yra sukuriams
//AuthController klaseje

//Sitas kol kas nenaudojamas.
//Paskui isvis reikes istrinti



package lt.sventes.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import lt.sventes.cart.model.Cart;
import lt.sventes.roles.service.RoleRepository;
import lt.sventes.users.models.RoleName;
import lt.sventes.users.models.User;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	// Naujo vartotojo sukūrimas - NEŠITAS METODAS NAUDOJAMAS
	@Transactional
	public void createUser(String name, String username, String email, String password, String role) {
			
		User newUser = new User(name, username, email, password);

		// TODO jeigu darysiu, tai cia reikia pabaigti
		// Role userRoleToAdd = findByName(RoleName role);
		// newUser.addMoreRoles(userRoleToAdd);
		userRepository.save(newUser);
		log.info("Sukurtas naujas vartotojas " + username);
		RoleName roleName = RoleName.valueOf(role);
		// Role providedRole = roleRepository.findByName(roleName);
		// newUser.addMoreRoles(providedRole);
			
		}

	// Krepšelių kūrimas
	@Transactional
	public void createNewCart(String username, Cart cart) {
		User currentUser = userRepository.findUserByUsername(username);
		if (cart.getUser() == null) {
			cart.setUser(currentUser);
		}
		if (cart.getUser().getUsername().equals(currentUser.getUsername())) {
			currentUser.addCartsToUser(cart);
		} else {
			// reikalingas klaidos apdirbimas
		}
		userRepository.save(currentUser);
		log.info("A new cart created for user (" + username + ")");

	}

}
