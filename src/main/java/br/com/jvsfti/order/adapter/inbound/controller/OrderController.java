package br.com.jvsfti.order.adapter.inbound.controller;

import br.com.jvsfti.order.adapter.dto.response.ResponseDTO;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.inbound.SearchOrderUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final SearchOrderUseCasePort searchOrderUseCasePort;

    @GetMapping
    public ResponseEntity<ResponseDTO> searchOrders(@RequestParam("status") OrderStatusEnum status) {
        var response = searchOrderUseCasePort.findByStatus(status);
        return ResponseEntity.ok(ResponseDTO.toResponse(response));
    }

}
