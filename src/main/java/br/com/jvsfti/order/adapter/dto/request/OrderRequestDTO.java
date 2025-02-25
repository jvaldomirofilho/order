package br.com.jvsfti.order.adapter.dto.request;

import java.util.List;

public record OrderRequestDTO(String externalId, List<OrderItemRequestDTO> items) {
}
