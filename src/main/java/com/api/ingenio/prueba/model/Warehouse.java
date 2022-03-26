package com.api.ingenio.prueba.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "warehouses", schema = "logistica")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Valor requerido")
    @Size(max = 100)
    private String name;

    @Min(value=0, message = " solo acepta valores positivos" )
    private Integer type;

    @Column
    private Boolean enabled;

    public Warehouse(){}

            public Warehouse( String name, Integer type, Boolean enabled) {
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
