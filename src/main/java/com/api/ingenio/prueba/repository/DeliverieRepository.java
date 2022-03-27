package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Customers;
import com.api.ingenio.prueba.model.Deliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Deliveries Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface DeliverieRepository extends JpaRepository<Deliveries,Long> {

    List<Deliveries> findByCustomerId(Customers customerId);
}
