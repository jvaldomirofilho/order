package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.inbound.SearchOrderUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchOrderAdapter implements SearchOrderUseCasePort {

    private final GetOrderAdapter getOrder;

    @Override
    public List<Order> findByStatus(OrderStatusEnum status) {
        return getOrder.searchOrderByStatus(status);
    }
}
