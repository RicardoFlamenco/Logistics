package com.api.ingenio.prueba.controller;


import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Products;
import com.api.ingenio.prueba.repository.ProductRepository;
import com.api.ingenio.prueba.requestDTO.ProductDTO;
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

    /**
     * Method used to obtain Products' list. Returns list of Products on success
     *
     * @param name String search term.
     * @return ResponseEntity List Products detail.
     */
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

    /**
     * Method used to register a new Customer. Returns Customer on success
     *
     * @param p Products new product details.
     * @return ResponseEntity Products detail.
     */
    @PostMapping("/")
    public ResponseEntity<Products> createService(@Valid @RequestBody ProductDTO p){
        Products prod = productRepository.save(new Products(p.getName(), p.getDescription(), p.getPrice(), true));
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    /**
     * Method used to obtain an especific Product. Returns Product on success
     *
     * @param id Long search id.
     * @return ResponseEntity Products detail.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(@PathVariable("id") Long id){
        Products products = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    /**
     * Method used to update an especific Product. Returns Product on success
     *
     * @param id Long search id.
     * @return ResponseEntity Product updated detail.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Products> update(@PathVariable("id") Long id, @RequestBody ProductDTO request) {
        Products products  = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPrice(request.getPrice());

        return new ResponseEntity<>(productRepository.save(products), HttpStatus.OK);
    }

    /**
     * Method used to delete an especific Product.
     *
     * @param id Long search id.
     * @return ResponseEntity HttpStatus result code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
