package br.com.jvsfti.order.adapter.dto.request;

import java.math.BigDecimal;

public record OrderItemRequestDTO(String productCode, String productName, BigDecimal price, Integer quantity) {
}
