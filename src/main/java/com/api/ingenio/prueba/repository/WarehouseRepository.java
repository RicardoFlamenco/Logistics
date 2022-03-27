package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Warehouse Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
List<Warehouse> findByNameContaining(String name);
Warehouse findByType(Integer type);
}
