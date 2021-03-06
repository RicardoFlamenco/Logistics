package com.api.ingenio.prueba.model;

import javax.persistence.*;

/** Represents a Role.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "roles", schema = "logistics")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}