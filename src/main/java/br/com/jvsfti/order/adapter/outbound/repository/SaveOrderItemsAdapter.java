package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.adapter.outbound.repository.entity.OrderItemEntity;
import br.com.jvsfti.order.domain.model.OrderItem;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderItemsPortAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SaveOrderItemsAdapter implements SaveOrderItemsPortAdapter {

    private final OrderItemEntityRepository orderItemEntityRepository;

    @Override
    public Set<OrderItem> saveOrderItems(Set<OrderItem> orderItems) {
        var orderItemEntitySet = orderItems.stream().map(OrderItemEntity::toEntity);
        orderItemEntitySet.forEach(orderItemEntityRepository::save);
        return orderItems;
    }
}
