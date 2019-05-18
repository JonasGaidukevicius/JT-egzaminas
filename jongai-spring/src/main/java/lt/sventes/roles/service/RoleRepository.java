package lt.sventes.roles.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.sventes.users.models.Role;
import lt.sventes.users.models.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Originalus metodas
	Optional<Role> findByName(RoleName roleName);
    //Mano metodas
	//Role findByName(RoleName roleName);
}