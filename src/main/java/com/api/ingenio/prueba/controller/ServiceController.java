package com.api.ingenio.prueba.controller;

import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Service;
import com.api.ingenio.prueba.repository.ServiceRepository;
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



    @PostMapping("/")
    public ResponseEntity<Service> create(@Valid @RequestBody Service ser){
        Service s = serviceRepository.save(new Service(ser.getName(), ser.getDescription()));
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> findById(@PathVariable("id") Integer id){
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(service, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable("id") Integer id, @RequestBody Service request) {
        Service service  = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        service.setName(request.getName());
        service.setDescription(request.getDescription());

        return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        serviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
