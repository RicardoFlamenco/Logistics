package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Service Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    List<Service> findByNameContaining(String name);

}
