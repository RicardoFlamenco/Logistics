package com.api.ingenio.prueba.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "products", schema = "logistics")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank(message = "el nombre es requerido")
    @Column
    private String name;

    @Column
    private String description;

    @PositiveOrZero(message = "el valor del precio debe de ser positivo")
    @Column
    private BigDecimal price;

    @Column
    private Boolean enabled;

    public Products(){    }
    public Products(String name, String description, BigDecimal price, Boolean enabled) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
