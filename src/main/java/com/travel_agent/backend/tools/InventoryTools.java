package com.travel_agent.backend.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InventoryTools {

    //    Test data for inventory
    private final Map<String, Integer> inventory = new HashMap<>(Map.of(
            "Headphone", 45,
            "Mobile", 50,
            "laptop", 15,
            "Monitor", 20,
            "Tablet", 25,
            "Smartwatch", 18,
            "Camera", 12,
            "Printer", 10,
            "Router", 8,
            "Speaker", 0
    ));


    @Tool(description = "Check the inventory for an existing product by its name")
    public String checkInventory(String productName) {
        log.info("Checking if product with name {}", productName);
        String existingProduct = inventory.keySet()
                .stream()
                .filter(product -> product.equalsIgnoreCase(productName.trim()))
                .findFirst()
                .orElse(null);

        if (existingProduct == null) {
            return "Product " + productName + " not found in inventory.";
        }

        Integer quantity = inventory.get(existingProduct);

        if (quantity == 0) {
            return existingProduct + " is currently out of stock.";
        }
        return "Current inventory for " + existingProduct + " is: " + quantity;
    }

    @Tool(description = "Update the existing inventory quantity for a product based on product name, ignore the cases. Do not create new products.")
    public String updateInventory(String productName, int newQuantity) {
        log.info("Updating inventory for product: {}", productName);
        if (newQuantity < 0) {
            return "Quantity cannot be negative.";
        }

        inventory.put(productName, newQuantity);
        return "Inventory updated for " + productName + " to " + newQuantity;
    }

    @Tool(description = """
    Increase the stock quantity of an EXISTING product.
    Use this tool when the customer says:
    'Add quantity', 'Add stock', 'Increase stock',
    or 'Add N units of a product'.
    Do not create a new product.
    """)
    public String addProductQuantity(String productName, int quantityToAdd) {
        log.info("Adding quantity {} to product: {}", quantityToAdd, productName);
        if (quantityToAdd < 0) {
            return "Quantity to add cannot be negative.";
        }

        String existingProduct = inventory.keySet()
                .stream()
                .filter(product -> product.equalsIgnoreCase(productName.trim()))
                .findFirst()
                .orElse(null);

        if (existingProduct == null) {
            return "Product " + productName + " not found in inventory.";
        }

        int currentQuantity = inventory.get(existingProduct);
        int updatedQuantity = currentQuantity + quantityToAdd;
        inventory.put(existingProduct, updatedQuantity);

        return "Inventory updated for " + existingProduct + ". New quantity: " + updatedQuantity;
    }

    @Tool(description = "Add a new product to the inventory with its name and quantity")
    public String addNewProduct(String productName, int quantity) {
        log.info("Adding new product: {}", productName);
        if (quantity < 0) {
            return "Quantity cannot be negative.";
        }

        if (inventory.containsKey(productName)) {
            return "Product " + productName + " already exists in inventory.";
        }

        inventory.put(productName, quantity);
        return "New product " + productName + " added to inventory with quantity " + quantity;
    }

    @Tool(description = "Get the total number of products in the inventory")
    public Integer getTotalProductsCountInventory() {
        log.info("Calling getTotalProductsCountInventory inside Inventory tool");
        return inventory.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Tool(description = "Get the total number of unique products in the inventory")
    public Integer getTotalUniqueProductsInventory() {
        log.info("Calling getTotalUniqueProductsInventory inside Inventory tool");
        return inventory.size();
    }

    @Tool(description = "Get all inventory details")
    public Map<String, Integer> getAllInventoryDetails() {
        log.info("Calling getAllInventoryDetails inside Inventory tool");
        return new HashMap<>(inventory);
    }


}