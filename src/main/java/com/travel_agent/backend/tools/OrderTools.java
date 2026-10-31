package com.travel_agent.backend.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OrderTools {

    // Dummy data for testing
    private final Map<String, String> orders = new HashMap<>(Map.of(
            "101", "Shipped- Arriving tomorrow",
            "102", "Processing- Expected to ship in 2 days",
            "103", "Delivered- Delivered on 2026-08-15",
            "104", "Cancelled- Order was cancelled by the user",
            "105", "Returned- Order was returned by the user",
            "106", "Order shipped - Expected to arrive in 3 days"
    ));

    @Tool(description = "Get the status of the customer's order by its ID")
    public String getOrderStatus(String orderId) {
        log.info("Calling getOrderStatus inside Order tool");
        log.info("Fetching order status for order ID: {}", orderId);
        return orders.getOrDefault(orderId, "Order ID not found");
    }


    @Tool(description = "Cancel an order only if it has not been shipped or delivered")
    public String cancelOrder(String orderId) {
        log.info("Calling cancelOrder inside Order tool");
        log.info("Cancelling order with ID: {}", orderId);

        String currentStatus = orders.get(orderId);

        if (currentStatus == null) {
            log.warn("Order ID not found: {}", orderId);
            return "Order ID not found: " + orderId;
        }

        String status = currentStatus.toLowerCase();

        if (status.contains("shipped") || status.contains("delivered")) {
            log.info("Cancellation rejected for order {}. Status: {}",
                    orderId, currentStatus);

            return "Order " + orderId
                    + " cannot be cancelled because it has already been shipped or delivered. "
                    + "Current status: " + currentStatus;
        }

        if (status.contains("cancelled") || status.contains("returned")) {
            return "Order " + orderId
                    + " cannot be cancelled. Current status: " + currentStatus;
        }

        orders.put(orderId, "Cancelled- Order was cancelled by the user");

        log.info("Order {} cancelled successfully", orderId);

        return "Order " + orderId + " has been cancelled successfully.";
    }


    @Tool(description = "Get the total count of customer orders")
    public Integer getOrderCount() {
        log.info("Calling getOrderCount inside Order tool");
        return orders.size();
    }

    @Tool(description = "Get a list of all customer orders")
    public Map<String, String> getAllOrders() {
        log.info("Calling getAllOrders inside Order tool");
        return orders;
    }

}
