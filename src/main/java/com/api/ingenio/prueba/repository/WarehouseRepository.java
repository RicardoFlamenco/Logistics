package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
List<Warehouse> findByNameContaining(String name);
Warehouse findByType(Integer type);
}
