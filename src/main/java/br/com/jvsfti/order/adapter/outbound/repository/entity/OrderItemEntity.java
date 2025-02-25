package br.com.jvsfti.order.adapter.outbound.repository.entity;


import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    private String productCode;

    private String productName;

    private BigDecimal price;

    private int quantity;

    public static OrderItemEntity toEntity(OrderItem orderItem) {
        return OrderItemEntity.builder()
                .id(orderItem.getId())
                .order(OrderEntity.toEntity(orderItem.getOrder()))
                .productCode(orderItem.getProductCode())
                .productName(orderItem.getProductName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static OrderItem toModel(OrderItemEntity orderItem) {
        var order = new Order(orderItem.getOrder().getId());
        return new OrderItem(orderItem.getId(), order, orderItem.getProductCode(), orderItem.getProductName(), orderItem.getPrice(), orderItem.getQuantity());
    }
}
