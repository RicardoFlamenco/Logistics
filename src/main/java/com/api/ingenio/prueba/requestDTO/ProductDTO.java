package com.api.ingenio.prueba.requestDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/** Represents a Product for request.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean enabled;

    public ProductDTO(){    }
    public ProductDTO(String name, String description, BigDecimal price, Boolean enabled) {
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
