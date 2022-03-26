package com.api.ingenio.prueba.requestDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DeliveryRequest implements Serializable {

    private Integer serviceId;
    private Long productId;
    private LocalDateTime deliverieDate;
    private Integer quantity;
    private Long customerId;
    private String number;
    private Long warehouseId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getDeliverieDate() {
        return deliverieDate;
    }

    public void setDeliverieDate(LocalDateTime deliverieDate) {
        this.deliverieDate = deliverieDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
