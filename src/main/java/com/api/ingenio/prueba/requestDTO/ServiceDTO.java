package com.api.ingenio.prueba.requestDTO;

import java.io.Serializable;

/** Represents a Service.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
public class ServiceDTO implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Integer discountRate;

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public ServiceDTO(String name, String description, Integer discountRate) {
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
    }

    public ServiceDTO(){}

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
