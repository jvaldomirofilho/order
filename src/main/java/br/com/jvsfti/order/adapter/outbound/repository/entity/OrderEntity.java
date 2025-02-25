package br.com.jvsfti.order.adapter.outbound.repository.entity;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderItem;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.flywaydb.core.internal.util.CollectionsUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String externalId;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    private BigDecimal totalValue;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItemEntity> orderItems;

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .externalId(order.getExternalId())
                .status(order.getStatus())
                .totalValue(order.getTotalValue())
                .createdAt(order.getCreatedAt())
                .build();
    }


    public static Order toModel(OrderEntity order) {
        Set<OrderItem> orderItems = null;
        if (CollectionsUtils.hasItems(order.getOrderItems())) {
            orderItems = order.getOrderItems().stream().map(OrderItemEntity::toModel).collect(Collectors.toSet());
        }
        return new Order(order.getId(), order.getExternalId(), order.getStatus(), order.getTotalValue(), order.getCreatedAt(), orderItems);
    }

}
