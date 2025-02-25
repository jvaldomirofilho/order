package br.com.jvsfti.order.domain.model;

import br.com.jvsfti.order.domain.exception.OrderExistingExecption;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Order {

    private UUID id;
    private String externalId;
    private OrderStatusEnum status;
    private BigDecimal totalValue;
    private LocalDateTime createdAt;
    private Set<OrderItem> orderItems;

    public Order() {}

    public Order(UUID id) {
        this.id = id;
    }

    public Order(String externalId, Set<OrderItem> orderItems) {
        this.createdAt = LocalDateTime.now();
        this.externalId = externalId;
        this.orderItems = orderItems;
    }

    public Order(UUID id, String externalId, OrderStatusEnum status, BigDecimal totalValue, LocalDateTime createdAt, Set<OrderItem> orderItems) {
        this.id = id;
        this.externalId = externalId;
        this.status = status;
        this.totalValue = totalValue;
        this.createdAt = createdAt;
        this.orderItems = orderItems;
    }

    public void process() {
        calculateTotalValue();
        this.status = OrderStatusEnum.PROCESSED;
    }

    private void calculateTotalValue() {
        this.totalValue = this.orderItems
                .stream()
                .map(o -> o.getPrice().multiply(new BigDecimal(o.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void validate(Order order) {
        if (Objects.nonNull(order)) {
            throw new OrderExistingExecption("Order already exists");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

}
