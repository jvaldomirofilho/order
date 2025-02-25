package br.com.jvsfti.order.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderItemTest {

    @Test
    void testConstructorWithProductDetails() {
        OrderItem item = new OrderItem("123", "Product A", BigDecimal.TEN, 5);

        assertEquals("123", item.getProductCode());
        assertEquals("Product A", item.getProductName());
        assertEquals(BigDecimal.TEN, item.getPrice());
        assertEquals(5, item.getQuantity());
    }

    @Test
    void testConstructorWithAllFields() {
        UUID id = UUID.randomUUID();
        Order order = new Order(UUID.randomUUID());
        OrderItem item = new OrderItem(id, order, "Product B", "456", BigDecimal.valueOf(15), 3);

        assertEquals(id, item.getId());
        assertEquals(order, item.getOrder());
        assertEquals("Product B", item.getProductName());
        assertEquals("456", item.getProductCode());
        assertEquals(BigDecimal.valueOf(15), item.getPrice());
        assertEquals(3, item.getQuantity());
    }

    @Test
    void testPrepareSave() {
        OrderItem item = new OrderItem();
        UUID orderId = UUID.randomUUID();
        item.prepareSave(orderId);

        assertNotNull(item.getOrder());
        assertEquals(orderId, item.getOrder().getId());
    }

    @Test
    void testGettersAndSetters() {
        OrderItem item = new OrderItem();
        UUID id = UUID.randomUUID();
        Order order = new Order(UUID.randomUUID());
        item.setId(id);
        item.setOrder(order);
        item.setProductCode("789");
        item.setProductName("Product C");
        item.setPrice(BigDecimal.valueOf(20));
        item.setQuantity(2);

        assertEquals(id, item.getId());
        assertEquals(order, item.getOrder());
        assertEquals("789", item.getProductCode());
        assertEquals("Product C", item.getProductName());
        assertEquals(BigDecimal.valueOf(20), item.getPrice());
        assertEquals(2, item.getQuantity());
    }
}
