package br.com.jvsfti.order.domain.ports.outbound;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;

import java.util.List;

public interface GetOrderPortAdapter {

    Order getByExternalId(String externalId);
    List<Order> searchOrderByStatus(OrderStatusEnum status);

}
