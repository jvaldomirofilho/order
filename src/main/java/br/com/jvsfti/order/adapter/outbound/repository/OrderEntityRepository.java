package br.com.jvsfti.order.adapter.outbound.repository;

import br.com.jvsfti.order.adapter.outbound.repository.entity.OrderEntity;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, UUID> {

    Optional<OrderEntity> findByExternalId(String externalId);

    @Query("SELECT o FROM OrderEntity o " +
            "JOIN FETCH o.orderItems item " +
            "WHERE o.status = :status")
    List<OrderEntity> findAllByStatus(OrderStatusEnum status);

}
