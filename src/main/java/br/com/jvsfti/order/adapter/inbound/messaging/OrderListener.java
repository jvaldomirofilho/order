package br.com.jvsfti.order.adapter.inbound.messaging;

import br.com.jvsfti.order.adapter.dto.request.OrderRequestDTO;
import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderItem;
import br.com.jvsfti.order.domain.ports.inbound.ProcessOrderUserCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessOrderUserCasePort createOrderUserCasePort;

    @Transactional
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void createOrderConsumer(final OrderRequestDTO orderRequest) {
        var items = orderRequest.items().stream().map(i -> new OrderItem(i.productCode(), i.productName(), i.price(), i.quantity())).collect(Collectors.toSet());
        var order = new Order(orderRequest.externalId(), items);
        createOrderUserCasePort.execute(order);
    }

}
