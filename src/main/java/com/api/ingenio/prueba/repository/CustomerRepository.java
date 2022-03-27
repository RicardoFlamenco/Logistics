package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Customer Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

    List<Customers> findByNameContaining(String name);

}
