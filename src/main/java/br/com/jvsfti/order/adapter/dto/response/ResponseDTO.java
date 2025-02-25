package br.com.jvsfti.order.adapter.dto.response;

import br.com.jvsfti.order.domain.model.Order;

import java.util.ArrayList;
import java.util.List;

public record ResponseDTO(List<OrderResponseDTO> orders) {


    public static ResponseDTO toResponse(List<Order> orders) {
        var ordersReponse = new ArrayList<OrderResponseDTO>();
        orders.forEach(order -> {
            var items = order.getOrderItems().stream().map(i -> new OrderItemResponseDTO(i.getProductCode(), i.getProductName(), i.getPrice(), i.getQuantity())).toList();
            var orderResponse = new OrderResponseDTO(order.getExternalId(), order.getStatus(), order.getTotalValue(), order.getCreatedAt(), items);
            ordersReponse.add(orderResponse);
        });

        return new ResponseDTO(ordersReponse);
    }

}