package com.api.ingenio.prueba.controller;


import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.*;
import com.api.ingenio.prueba.requestDTO.DeliveryRequest;
import com.api.ingenio.prueba.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/deliveries")
public class DeliveriesController {

    @Autowired
    DeliverieRepository deliverieRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    /**
     * Method used to obtain all deliveries made to a customer.
     *
     * @param id Long search client id.
     * @return ResponseEntity List Deliveries details.
     */
    @GetMapping("/allByClient/{id}")
    public ResponseEntity<List<Deliveries>> getAll(@PathVariable("id") Long id) {
        List<Deliveries> deliveries = new ArrayList<Deliveries>();
        Customers cus = new Customers();
        if (id <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + id);
        else
            cus = customerRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con este identificador = " + id));

        deliveries = deliverieRepository.findByCustomerId(cus);
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    /**
     * Method used to create a new delivery.
     *
     * @param request DeliveryRequest delivery detail.
     * @return ResponseEntity Deliveries new delivery details.
     */
    @PostMapping("/")
    public ResponseEntity<Deliveries> createDeliverie(@Valid @RequestBody DeliveryRequest request){
        Deliveries deliveries= new Deliveries();
        deliveries.setCustomerId(new Customers());
        deliveries.setProductId(new Products());
        deliveries.setServiceId(new Service());
        deliveries.setWarehouseId(new Warehouse());

        Pattern pattern1 ;
        Matcher matcher1 ;
        if(valid(request)){
            deliveries.setCustomerId(customerRepository.findById(request.getCustomerId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con este identificador = " + request.getCustomerId())));
            deliveries.setServiceId(serviceRepository.findById(request.getServiceId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se servicio configurado = " + request.getServiceId())));
            deliveries.setWarehouseId(warehouseRepository.findById(request.getWarehouseId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se servicio configurado = " + request.getWarehouseId())));

            deliveries.setProductId(productRepository.findById(request.getProductId()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra este producto= " + request.getProductId())));

            if(deliveries.getServiceId().getId()==1){
                pattern1 = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
            }else{
                pattern1 =Pattern.compile("^[A-Z]{3}[0-9]{4}[A-Z]{1}$");
            }
            matcher1 = pattern1.matcher(request.getNumber());
            if(!matcher1.find()){
                throw new ResourceNotFoundException("Numero de transporte no coincide con el formato");
            }
            deliveries.setTransportNumber(request.getNumber());
            deliveries.setTrackingNumber(generate());
            deliveries.setDeliverieDate(request.getDeliverieDate());
            deliveries.setEnabled(true);
            deliveries.setQuantity(request.getQuantity());
            deliveries.setSubTotal(deliveries.getProductId().getPrice().multiply(new BigDecimal(deliveries.getQuantity())));
            if(deliveries.getQuantity()>=10 ){
                deliveries.setDiscount(deliveries.getSubTotal().multiply(new BigDecimal(deliveries.getServiceId().getDiscountRate())));
            }else{
                deliveries.setDiscount(BigDecimal.ZERO);
            }

            deliveries.setTotal(deliveries.getSubTotal().subtract(deliveries.getDiscount()));
            deliveries.setCreated(LocalDateTime.now());

            deliverieRepository.save(deliveries);
            return new ResponseEntity<>(deliveries, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Boolean valid (DeliveryRequest d){

        //cliente
        if (d.getCustomerId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + d.getCustomerId());

        //servicio
        if (d.getServiceId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + d.getCustomerId());

        // producto
        if (d.getProductId() <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un cliente existente  " + d.getCustomerId());
        //cantidad
        if (d.getQuantity() <= 0 )
            throw  new ResourceNotFoundException("la cantidad de producto es requerido " + d.getQuantity());

        //fecha de entrega
        if(d.getDeliverieDate() == null)
            throw  new ResourceNotFoundException("fecha de entrega es requerida" + d.getQuantity());
        // numero de transporte
        if(d.getNumber() == null)
            throw  new ResourceNotFoundException("el numero de transporte es requerido" + d.getQuantity());

        return true;
    }

    public String  generate() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }


}
