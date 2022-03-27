package com.api.ingenio.prueba.requestDTO;

import java.io.Serializable;
import java.time.LocalDateTime;


/** Represents a Customer.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */

public class CustomerDTO implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private Boolean active;
    private LocalDateTime created;

    public void CustomerDTO() {
        //Do Something
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}
