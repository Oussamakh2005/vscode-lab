package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printOrderSummary_shouldApplyDiscountForMember() {
        // Create member customer
        Customer customer = new Customer();
        setField(customer, "name", "Alice");
        setField(customer, "isMember", true);

        // Create items
        Item item1 = new Item();
        setField(item1, "name", "Book");
        setField(item1, "price", 20.0);
        setField(item1, "quantity", 2);

        Item item2 = new Item();
        setField(item2, "name", "Pen");
        setField(item2, "price", 5.0);
        setField(item2, "quantity", 3);

        Order order = new Order();
        setField(order, "customer", customer);
        setField(order, "items", List.of(item1, item2));

        processor.printOrderSummary(order);

        String output = outContent.toString();
        // Expected total = (20*2 + 5*3) * 0.9 = (40+15)*0.9 = 55*0.9 = 49.5
        assertTrue(output.contains("Total Price: $49.50"));
        assertTrue(output.contains("Customer: Alice"));
        assertTrue(output.contains("- Book: 2 x $20.0 = $40.0"));
        assertTrue(output.contains("- Pen: 3 x $5.0 = $15.0"));
    }

    @Test
    void printOrderSummary_shouldNotApplyDiscountForNonMember() {
        Customer customer = new Customer();
        setField(customer, "name", "Bob");
        setField(customer, "isMember", false);

        Item item = new Item();
        setField(item, "name", "Laptop");
        setField(item, "price", 1000.0);
        setField(item, "quantity", 1);

        Order order = new Order();
        setField(order, "customer", customer);
        setField(order, "items", List.of(item));

        processor.printOrderSummary(order);

        String output = outContent.toString();
        assertTrue(output.contains("Total Price: $1000.00"));
        assertTrue(output.contains("- Laptop: 1 x $1000.0 = $1000.0"));
    }

    @Test
    void printOrderSummary_shouldHandleEmptyItems() {
        Customer customer = new Customer();
        setField(customer, "name", "Charlie");
        setField(customer, "isMember", true);

        Order order = new Order();
        setField(order, "customer", customer);
        setField(order, "items", List.of());

        processor.printOrderSummary(order);

        String output = outContent.toString();
        assertTrue(output.contains("Total Price: $0.00"));
        assertTrue(output.contains("Items:")); // No item lines
    }

    // Helper to set private fields via reflection (simplifies test setup)
    private void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
