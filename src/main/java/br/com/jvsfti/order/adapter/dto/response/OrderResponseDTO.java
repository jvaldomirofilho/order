package br.com.jvsfti.order.adapter.dto.response;

import br.com.jvsfti.order.domain.model.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(String externalId,
                               OrderStatusEnum status,
                               BigDecimal totalValue,
                               LocalDateTime createdAt,
                               List<OrderItemResponseDTO> orderItems){}
