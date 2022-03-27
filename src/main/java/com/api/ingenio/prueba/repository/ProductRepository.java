package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Products Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    List<Products> findByNameContainingAndEnabledIsTrue(String name);

}
