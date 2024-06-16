package br.com.achaweb.repositories;

import br.com.achaweb.entities.Product;
import br.com.achaweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
