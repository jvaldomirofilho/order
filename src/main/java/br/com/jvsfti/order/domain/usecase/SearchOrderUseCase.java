package br.com.jvsfti.order.domain.usecase;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.inbound.SearchOrderUseCasePort;
import br.com.jvsfti.order.domain.ports.outbound.GetOrderPortAdapter;

import java.util.List;

public class SearchOrderUseCase implements SearchOrderUseCasePort {

    private final GetOrderPortAdapter getOrderPortAdapter;

    public SearchOrderUseCase(GetOrderPortAdapter getOrderPortAdapter) {
        this.getOrderPortAdapter = getOrderPortAdapter;
    }

    @Override
    public List<Order> findByStatus(OrderStatusEnum status) {
        return getOrderPortAdapter.searchOrderByStatus(status);
    }
}
