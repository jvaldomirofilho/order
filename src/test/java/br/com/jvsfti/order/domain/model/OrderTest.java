package br.com.jvsfti.order.domain.model;

import br.com.jvsfti.order.domain.exception.OrderExistingExecption;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testConstructorWithExternalIdAndOrderItems() {
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));
        orderItems.add(new OrderItem("2", "Product2", BigDecimal.valueOf(5), 3));

        Order order = new Order("12345", orderItems);

        assertNotNull(order.getCreatedAt());
        assertEquals("12345", order.getExternalId());
        assertEquals(orderItems, order.getOrderItems());
    }

    @Test
    void testConstructorWithAllFields() {
        UUID id = UUID.randomUUID();
        String externalId = "12345";
        OrderStatusEnum status = OrderStatusEnum.PROCESSED;
        BigDecimal totalValue = BigDecimal.valueOf(35);
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));
        orderItems.add(new OrderItem("2", "Product2", BigDecimal.valueOf(5), 3));

        Order order = new Order(id, externalId, status, totalValue, createdAt, orderItems);

        assertEquals(id, order.getId());
        assertEquals(externalId, order.getExternalId());
        assertEquals(status, order.getStatus());
        assertEquals(totalValue, order.getTotalValue());
        assertEquals(createdAt, order.getCreatedAt());
        assertEquals(orderItems, order.getOrderItems());
    }

    @Test
    void testProcess() {
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));
        orderItems.add(new OrderItem("2", "Product2", BigDecimal.valueOf(5), 3));

        Order order = new Order("12345", orderItems);
        order.process();

        assertEquals(OrderStatusEnum.PROCESSED, order.getStatus());
        assertEquals(BigDecimal.valueOf(35), order.getTotalValue());
    }

    @Test
    void testCalculateTotalValue() {
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));
        orderItems.add(new OrderItem("2", "Product2", BigDecimal.valueOf(5), 3));

        Order order = new Order("12345", orderItems);
        order.process();

        assertEquals(BigDecimal.valueOf(35), order.getTotalValue());
    }

    @Test
    void testValidate_OrderExists() {
        Order order = new Order("12345", new HashSet<>());
        assertThrows(OrderExistingExecption.class, () -> Order.validate(order));
    }

    @Test
    void testValidate_OrderDoesNotExist() {
        assertDoesNotThrow(() -> Order.validate(null));
    }

    @Test
    void testGettersAndSetters() {
        Order order = new Order();
        UUID id = UUID.randomUUID();
        String externalId = "12345";
        OrderStatusEnum status = OrderStatusEnum.PROCESSED;
        BigDecimal totalValue = BigDecimal.valueOf(35);
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);
        Set<OrderItem> orderItems = new HashSet<>();
        orderItems.add(new OrderItem("1","Product1", BigDecimal.TEN, 2));

        order.setId(id);
        order.setExternalId(externalId);
        order.setStatus(status);
        order.setTotalValue(totalValue);
        order.setCreatedAt(createdAt);
        order.setOrderItems(orderItems);

        assertEquals(id, order.getId());
        assertEquals(externalId, order.getExternalId());
        assertEquals(status, order.getStatus());
        assertEquals(totalValue, order.getTotalValue());
        assertEquals(createdAt, order.getCreatedAt());
        assertEquals(orderItems, order.getOrderItems());
    }
}