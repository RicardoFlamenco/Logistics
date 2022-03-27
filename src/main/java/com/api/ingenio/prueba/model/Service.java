package com.api.ingenio.prueba.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** Represents a Service.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "services", schema = "logistics")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "el campo nombre es requerido")
    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String description;

    private Integer discountRate;

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public Service(@NotBlank(message = "el campo nombre es requerido") @Size(max = 50) String name, @Size(max = 100) String description, Integer discountRate) {
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
    }

    public Service (){}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
}
