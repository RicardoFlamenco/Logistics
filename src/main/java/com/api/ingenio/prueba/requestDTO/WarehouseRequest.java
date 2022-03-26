package com.api.ingenio.prueba.requestDTO;

import javax.validation.constraints.*;
import java.io.Serializable;


public class WarehouseRequest implements Serializable {

    @NotBlank(message = "valor requerido")
    @NotNull(message = "valor requerido")
    @Size(max = 100)
    private String name;
    @Min(value=0, message = "solo acepta valores positivos" )
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
