package com.api.ingenio.prueba.requestDTO;

import java.io.Serializable;

/** Represents a Warehouse.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
public class WarehouseDTO implements Serializable {

    private Long id;
    private String name;
    private Integer type;
    private Boolean enabled;

    public WarehouseDTO(){}

            public WarehouseDTO(String name, Integer type, Boolean enabled) {
        this.name = name;
        this.type = type;
        this.enabled=enabled;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
