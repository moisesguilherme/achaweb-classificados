package br.com.achaweb.repositories;

import br.com.achaweb.entities.Role;
import br.com.achaweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
