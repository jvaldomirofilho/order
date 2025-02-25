package br.com.jvsfti.order.domain.ports.inbound;

import br.com.jvsfti.order.domain.model.Order;

public interface ProcessOrderUserCasePort {

    Order execute(Order order);

}
