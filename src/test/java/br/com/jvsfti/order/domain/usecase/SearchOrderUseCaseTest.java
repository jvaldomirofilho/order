package br.com.jvsfti.order.domain.usecase;

import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.outbound.GetOrderPortAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchOrderUseCaseTest {

    @Mock
    private GetOrderPortAdapter getOrderPortAdapter;

    @InjectMocks
    private SearchOrderUseCase searchOrderUseCase;

    @Test
    void testFindByStatus() {
        OrderStatusEnum status = OrderStatusEnum.PROCESSED;
        List<Order> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Order("12345", null));
        expectedOrders.add(new Order("67890", null));

        when(getOrderPortAdapter.searchOrderByStatus(status)).thenReturn(expectedOrders);

        List<Order> actualOrders = searchOrderUseCase.findByStatus(status);

        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void testFindByStatus_EmptyList() {
        OrderStatusEnum status = OrderStatusEnum.PROCESSED;
        List<Order> expectedOrders = new ArrayList<>();

        when(getOrderPortAdapter.searchOrderByStatus(status)).thenReturn(expectedOrders);

        List<Order> actualOrders = searchOrderUseCase.findByStatus(status);

        assertEquals(expectedOrders, actualOrders);
    }
}