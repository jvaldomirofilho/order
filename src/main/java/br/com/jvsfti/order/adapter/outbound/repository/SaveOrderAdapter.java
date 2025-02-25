package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.adapter.outbound.repository.entity.OrderEntity;
import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderPortAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveOrderAdapter implements SaveOrderPortAdapter {

    private final OrderEntityRepository orderEntityRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderSaved = OrderEntity.toEntity(order);
        return OrderEntity.toModel(orderEntityRepository.save(orderSaved));
    }
}
