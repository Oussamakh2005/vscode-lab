package org.example;

import java.util.List;

import java.util.List;

public class OrderProcessor {
    private static final double MEMBER_DISCOUNT_RATE = 0.9;

    public void printOrderSummary(Order order) {
        double totalPrice = calculateTotalPrice(order.getItems());

        if (order.getCustomer().isMember()) {
            totalPrice *= MEMBER_DISCOUNT_RATE;
        }

        printSummaryHeader(order);
        printAllItems(order.getItems());
        System.out.printf("Total Price: $%.2f%n", totalPrice);
    }

    private double calculateTotalPrice(List<Item> items) {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    private void printSummaryHeader(Order order) {
        System.out.println("Order Summary:");
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Items:");
    }

    private void printAllItems(List<Item> items) {
        for (Item item : items) {
            printItemLine(item);
        }
    }

    private void printItemLine(Item item) {
        // Preserve original output format exactly
        System.out.println("  - " + item.getName() + ": " + item.getQuantity() + " x $" + item.getPrice() + " = $" + (item.getQuantity() * item.getPrice()));
    }
}