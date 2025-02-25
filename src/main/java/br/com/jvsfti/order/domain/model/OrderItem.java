package br.com.jvsfti.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {

    private UUID id;
    private Order order;
    private String productCode;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public OrderItem() {}

    public OrderItem(String productCode, String productName, BigDecimal price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(UUID id, Order order, String productName, String productCode, BigDecimal price, int quantity) {
        this.id = id;
        this.order = order;
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
        this.quantity = quantity;
    }

    public void prepareSave(UUID orderId) {
        this.order = new Order(orderId);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
