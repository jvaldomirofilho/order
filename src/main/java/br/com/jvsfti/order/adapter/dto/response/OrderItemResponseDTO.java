package br.com.jvsfti.order.adapter.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(String productCode,
                                   String productName,
                                   BigDecimal price,
                                   int quantity){}
