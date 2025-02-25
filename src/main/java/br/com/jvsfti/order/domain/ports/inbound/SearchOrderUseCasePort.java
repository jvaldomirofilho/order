package br.com.jvsfti.order.domain.ports.inbound;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;

import java.util.List;

public interface SearchOrderUseCasePort {

    List<Order> findByStatus(OrderStatusEnum status);

}
