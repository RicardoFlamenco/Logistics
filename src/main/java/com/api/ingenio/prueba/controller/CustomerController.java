package com.api.ingenio.prueba.controller;

import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Customers;
import com.api.ingenio.prueba.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Method used to register a new Customer. Returns Customer on success
     *
     * @param cus Customers new customer details.
     * @return ResponseEntity Customers detail.
     */
    @PostMapping("/")
    public ResponseEntity<Customers> createCustomer(@RequestBody Customers cus){
        LocalDateTime lt = LocalDateTime.now();
        Customers c = customerRepository.save(new Customers(cus.getName(),cus.getPhone(),cus.getAddress(),cus.getEmail(),lt,cus.getActive()));
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }


    /**
     * Method used to obtain Customers' list. Returns list of Customers on success
     *
     * @param name String search term.
     * @return ResponseEntity List Customers detail.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Customers>> getAll(@RequestParam(required = false) String name) {
        List<Customers> ser = new ArrayList<Customers>();
        if (name == null)
            customerRepository.findAll().forEach(ser::add);
        else
            customerRepository.findByNameContaining(name).forEach(ser::add);

        if (ser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ser, HttpStatus.OK);
    }


    /**
     * Method used to obtain an especific Customer. Returns Customer on success
     *
     * @param id Long search id.
     * @return ResponseEntity Customers detail.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customers> findById(@PathVariable("id") Long id){
        Customers customers = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(customers, HttpStatus.OK);

    }

    /**
     * Method used to update an especific Customer. Returns Customer on success
     *
     * @param id Long search id.
     * @return ResponseEntity Customer updated detail.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customers> update(@PathVariable("id") Long id, @RequestBody Customers request) {
        Customers customers  = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        customers.setName(request.getName());
        customers.setEmail(request.getEmail());
        customers.setAddress(request.getAddress());
        customers.setPhone(request.getPhone());

        return new ResponseEntity<>(customerRepository.save(customers), HttpStatus.OK);
    }

    /**
     * Method used to delete an especific Customer.
     *
     * @param id Long search id.
     * @return ResponseEntity HttpStatus result code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Customers customers  = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));
        customers.setActive(false);
        customerRepository.save(customers);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
