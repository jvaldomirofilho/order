package br.com.jvsfti.order.domain.ports.outbound;

import br.com.jvsfti.order.domain.model.OrderItem;

import java.util.Set;

public interface SaveOrderItemsPortAdapter {

    Set<OrderItem> saveOrderItems(Set<OrderItem> orderItems);

}
