package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.adapter.outbound.repository.entity.OrderEntity;
import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.outbound.GetOrderPortAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetOrderAdapter implements GetOrderPortAdapter {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderItemEntityRepository orderItemEntityRepository;

    @Override
    public Order getByExternalId(String externalId) {
        var orderEntity = orderEntityRepository.findByExternalId(externalId).orElse(null);
        return Objects.nonNull(orderEntity) ? OrderEntity.toModel(orderEntity) : null;
    }

    @Override
    @Transactional
    public List<Order> searchOrderByStatus(OrderStatusEnum status) {
        var orderEntityList = orderEntityRepository.findAllByStatus(status);
        return orderEntityList.stream().map(OrderEntity::toModel).toList();
    }

}
