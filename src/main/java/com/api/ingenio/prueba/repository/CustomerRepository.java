package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.Customers;
import com.api.ingenio.prueba.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

    List<Customers> findByNameContaining(String name);

}
