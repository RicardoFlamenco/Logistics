package com.api.ingenio.prueba.controller;

import com.api.ingenio.prueba.execption.ResourceNotFoundException;
import com.api.ingenio.prueba.model.Warehouse;
import com.api.ingenio.prueba.repository.WarehouseRepository;
import com.api.ingenio.prueba.requestDTO.WarehouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/warehouse")
public class WareHouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    /**
     * Method used to obtain Warehouse list. Returns list of Warehouse on success
     *
     * @param name String search term.
     * @return ResponseEntity List Warehouse detail.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWareHouse(@RequestParam(required = false) String name) {
        List<Warehouse> ware = new ArrayList<Warehouse>();
        if (name == null)
            warehouseRepository.findAll().forEach(ware::add);
        else
            warehouseRepository.findByNameContaining(name).forEach(ware::add);

        if (ware.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ware, HttpStatus.OK);
    }

    /**
     * Method used to register a new Warehouse. Returns Warehouse on success
     *
     * @param request Warehouse new warehouse details.
     * @return ResponseEntity Warehouse detail.
     */
    @PostMapping("/")
    public ResponseEntity<Warehouse> createWareHouse(@Valid @RequestBody WarehouseDTO request){
        Warehouse s = warehouseRepository.save(new Warehouse(request.getName(), request.getType(),true));
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }


    /**
     * Method used to obtain an especific Warehouse. Returns Warehouse on success
     *
     * @param id Long search id.
     * @return ResponseEntity Warehouse detail.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable("id") Long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el id = " + id));
        return new ResponseEntity<>(warehouse, HttpStatus.OK);

    }

    /**
     * Method used to update an especific Warehouse. Returns Warehouse on success
     *
     * @param id Long search id.
     * @return ResponseEntity Warehouse updated detail.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWareHouse(@PathVariable("id") Long id, @RequestBody WarehouseDTO request) {
        Warehouse  ware  = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro con id = " + id));

        ware.setName(request.getName());
        ware.setEnabled(request.getEnabled());
        ware.setType(request.getType());


        return new ResponseEntity<>(warehouseRepository.save(ware), HttpStatus.OK);
    }

    /**
     * Method used to delete an especific Warehouse.
     *
     * @param id Long search id.
     * @return ResponseEntity HttpStatus result code.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWareHouse(@PathVariable("id") Long id) {
        warehouseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
