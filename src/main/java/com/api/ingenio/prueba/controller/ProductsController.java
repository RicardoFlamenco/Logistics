package com.api.ingenio.prueba.controller;


import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Products;
import com.api.ingenio.prueba.model.Service;
import com.api.ingenio.prueba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/product")
public class ProductsController {


    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAll(@RequestParam(required = false) String name) {
        List<Products> products = new ArrayList<Products>();
        if (name == null)
            productRepository.findAll().forEach(products::add);
        else
            productRepository.findByNameContainingAndEnabledIsTrue(name).forEach(products::add);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody Products p){
        Products prod = productRepository.save(new Products(p.getName(), p.getDescription(), p.getPrice(), true));
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(@PathVariable("id") Long id){
        Products products = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> update(@PathVariable("id") Long id, @RequestBody Products request) {
        Products products  = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());

        return new ResponseEntity<>(productRepository.save(products), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
