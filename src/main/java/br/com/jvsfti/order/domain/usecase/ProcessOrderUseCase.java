package br.com.jvsfti.order.domain.usecase;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.ports.inbound.ProcessOrderUserCasePort;
import br.com.jvsfti.order.domain.ports.outbound.GetOrderPortAdapter;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderItemsPortAdapter;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderPortAdapter;

public class ProcessOrderUseCase implements ProcessOrderUserCasePort {

    private final GetOrderPortAdapter getOrderPortAdapter;
    private final SaveOrderPortAdapter saveOrderPortAdapter;
    private final SaveOrderItemsPortAdapter saveOrderItemsPortAdapter;

    public ProcessOrderUseCase(GetOrderPortAdapter getOrderPortAdapter, SaveOrderPortAdapter saveOrderPortAdapter, SaveOrderItemsPortAdapter saveOrderItemsPortAdapter) {
        this.getOrderPortAdapter = getOrderPortAdapter;
        this.saveOrderPortAdapter = saveOrderPortAdapter;
        this.saveOrderItemsPortAdapter = saveOrderItemsPortAdapter;
    }

    @Override
    public Order execute(Order order) {
        var existingOrder = getOrderPortAdapter.getByExternalId(order.getExternalId());
        Order.validate(existingOrder);
        order.process();
        var createdOrder = saveOrder(order);
        saveOrderItems(order, createdOrder);
        return createdOrder;
    }

    private Order saveOrder(Order order) {
        return saveOrderPortAdapter.saveOrder(order);
    }

    private void saveOrderItems(Order order, Order createdOrder) {
        order.getOrderItems()
                .forEach(orderItem -> orderItem.prepareSave(createdOrder.getId()));
        saveOrderItemsPortAdapter.saveOrderItems(order.getOrderItems());
    }
}
