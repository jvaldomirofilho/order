package br.com.jvsfti.order.domain.ports.outbound;

import br.com.jvsfti.order.domain.model.Order;

public interface SaveOrderPortAdapter {

    Order saveOrder(Order order);

}
