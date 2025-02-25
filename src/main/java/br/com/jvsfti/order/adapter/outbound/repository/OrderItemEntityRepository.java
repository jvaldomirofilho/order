package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.adapter.outbound.repository.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, UUID> {

    Set<OrderItemEntity> findByOrderId(UUID orderId);

}
