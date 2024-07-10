package br.com.achaweb.repositories;

import br.com.achaweb.entities.Category;
import br.com.achaweb.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Busca o produto junto com os objetos da categoria (não funciona com page. Somente com List)
    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :products")
    List<Product> findProductsWithCategories(List<Product> products);


    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats WHERE "
            + "(COALESCE(:categories) IS NULL OR cats IN :categories) AND "
            + "(:name = '' OR LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))) ")
    Page<Product> find(List<Category> categories, String name, Pageable pageable);


    @Query(value = "SELECT tp.* " +
            "FROM tb_region_groupcategory tr_gc " +
            "INNER JOIN tb_groupcategory tgc ON tgc.id = tr_gc.groupcategory_id " +
            "LEFT JOIN tb_groupcategory_category tgc_c ON tgc.id = tgc_c.groupcategory_id " +
            "LEFT JOIN tb_category tc ON tc.id = tgc_c.category_id " +
            "INNER JOIN tb_product_category tpc ON tpc.category_id = tc.id " +
            "INNER JOIN tb_product tp ON tp.id = tpc.product_id " +
            "WHERE tr_gc.city_id = :cityId", nativeQuery = true)
    List<Product> findByRegionOrCity(@Param("cityId") Long cityId);
}
