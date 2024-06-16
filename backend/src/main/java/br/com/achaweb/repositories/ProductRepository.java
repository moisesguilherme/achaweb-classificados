package br.com.achaweb.repositories;

import br.com.achaweb.entities.Category;
import br.com.achaweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
