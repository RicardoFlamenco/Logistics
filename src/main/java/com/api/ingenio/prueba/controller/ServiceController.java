package com.api.ingenio.prueba.controller;

import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Service;
import com.api.ingenio.prueba.repository.ServiceRepository;
import com.api.ingenio.prueba.requestDTO.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/service")
public class ServiceController {
    @Autowired
    ServiceRepository serviceRepository;

    /**
     * Method used to obtain Services list. Returns list of Services on success
     *
     * @param name String search term.
     * @return ResponseEntity List Service detail.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Service>> getAll(@RequestParam(required = false) String name) {
        List<Service> ser = new ArrayList<Service>();
        if (name == null)
            serviceRepository.findAll().forEach(ser::add);
        else
            serviceRepository.findByNameContaining(name).forEach(ser::add);

        if (ser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ser, HttpStatus.OK);
    }


    /**
     * Method used to register a new Customer. Returns Service on success
     *
     * @param ser Service new service details.
     * @return ResponseEntity Service detail.
     */
    @PostMapping("/")
    public ResponseEntity<Service> create(@Valid @RequestBody ServiceDTO ser){
        Service s = serviceRepository.save(new Service(ser.getName(), ser.getDescription(),ser.getDiscountRate()));
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    /**
     * Method used to obtain an especific Service. Returns Service on success
     *
     * @param id Long search id.
     * @return ResponseEntity Service detail.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Service> findById(@PathVariable("id") Integer id){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(service, HttpStatus.OK);

    }

    /**
     * Method used to update an especific Service. Returns Service on success
     *
     * @param id Long search id.
     * @return ResponseEntity Service updated detail.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable("id") Integer id, @RequestBody ServiceDTO request) {
        Service service  = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setDiscountRate(request.getDiscountRate());

        return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.OK);
    }

    /**
     * Method used to delete an especific Service.
     *
     * @param id Long search id.
     * @return ResponseEntity HttpStatus result code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        serviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
