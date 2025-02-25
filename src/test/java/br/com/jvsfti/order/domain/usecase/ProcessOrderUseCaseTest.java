package br.com.jvsfti.order.domain.usecase;

import br.com.jvsfti.order.domain.exception.OrderExistingExecption;
import br.com.jvsfti.order.domain.model.Order;
import br.com.jvsfti.order.domain.model.OrderItem;
import br.com.jvsfti.order.domain.model.OrderStatusEnum;
import br.com.jvsfti.order.domain.ports.outbound.GetOrderPortAdapter;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderItemsPortAdapter;
import br.com.jvsfti.order.domain.ports.outbound.SaveOrderPortAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessOrderUseCaseTest {

    @Mock
    private GetOrderPortAdapter getOrderPortAdapter;

    @Mock
    private SaveOrderPortAdapter saveOrderPortAdapter;

    @Mock
    private SaveOrderItemsPortAdapter saveOrderItemsPortAdapter;

    @InjectMocks
    private ProcessOrderUseCase processOrderUseCase;

    private Order order;
    private Set<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));
        orderItems.add(new OrderItem("2", "Product2", BigDecimal.valueOf(5), 3));

        order = new Order("12345", orderItems);
    }

    @Test
    void testExecute_NewOrder() {
        when(getOrderPortAdapter.getByExternalId(order.getExternalId())).thenReturn(null);
        when(saveOrderPortAdapter.saveOrder(any(Order.class))).thenReturn(order);

        Order result = processOrderUseCase.execute(order);

        assertEquals(OrderStatusEnum.PROCESSED, result.getStatus());
        assertEquals(BigDecimal.valueOf(35), result.getTotalValue());
        verify(saveOrderItemsPortAdapter).saveOrderItems(orderItems);
    }

    @Test
    void testExecute_ExistingOrder() {
        when(getOrderPortAdapter.getByExternalId(order.getExternalId())).thenReturn(order);

        assertThrows(OrderExistingExecption.class, () -> processOrderUseCase.execute(order));
    }

    @Test
    void testSaveOrder() {
        when(saveOrderPortAdapter.saveOrder(any(Order.class))).thenReturn(order);

        Order savedOrder = processOrderUseCase.execute(order);

        assertEquals(order, savedOrder);
    }

    @Test
    void testSaveOrderItems() {
        when(getOrderPortAdapter.getByExternalId(order.getExternalId())).thenReturn(null);
        when(saveOrderPortAdapter.saveOrder(any(Order.class))).thenReturn(order);

        processOrderUseCase.execute(order);

        verify(saveOrderItemsPortAdapter).saveOrderItems(orderItems);
    }
}