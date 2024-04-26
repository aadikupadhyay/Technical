import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem {
    private static Map<String, Item> inventory = new HashMap<>();
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("Inventory Management System");
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel itemNameLabel = new JLabel("Item Name:");
        JTextField itemNameField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(8);

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton checkButton = new JButton("Check Quantity");
        JButton displayButton = new JButton("Display Inventory");

        JLabel resultLabel = new JLabel();

        addButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            inventory.put(itemName, new Item(quantity, price));
            resultLabel.setText(quantity + " " + itemName + " added to inventory.");
        });

        removeButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            if (inventory.containsKey(itemName)) {
                Item item = inventory.get(itemName);
                if (item.getQuantity() >= quantity) {
                    item.removeQuantity(quantity);
                    resultLabel.setText(quantity + " " + itemName + " removed from inventory.");
                } else {
                    resultLabel.setText("Not enough " + itemName + " in inventory.");
                }
            } else {
                resultLabel.setText(itemName + " not found in inventory.");
            }
        });

        checkButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            Item item = inventory.getOrDefault(itemName, null);
            if (item != null) {
                resultLabel.setText("Available quantity of " + itemName + ": " + item.getQuantity());
            } else {
                resultLabel.setText(itemName + " not found in inventory.");
            }
        });

        displayButton.addActionListener(e -> {
            StringBuilder inventoryList = new StringBuilder("<html><body>");
            inventoryList.append("<b>Current Inventory:</b><br>");
            double totalInventoryValue = 0;
            int totalQuantity = 0;
            for (Map.Entry<String, Item> entry : inventory.entrySet()) {
                String itemName = entry.getKey();
                Item item = entry.getValue();
                double totalPrice = item.getTotalPrice();
                inventoryList.append(itemName).append(": ").append(item.getQuantity()).append(" (Price: ₹")
                        .append(item.getPrice()).append(" each, Total Price: ₹").append(totalPrice).append(")<br>");
                totalInventoryValue += totalPrice;
                totalQuantity += item.getQuantity();
            }
            inventoryList.append("<br><b>Total Inventory Value:</b> ₹").append(totalInventoryValue);
            inventoryList.append("<br><b>Total Quantity of Grocery Products:</b> ").append(totalQuantity);
            inventoryList.append("</body></html>");
            resultLabel.setText(inventoryList.toString());
        });

        panel.add(itemNameLabel);
        panel.add(itemNameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(checkButton);
        panel.add(displayButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class Item {
    private int quantity;
    private double price;

    public Item(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    public void removeQuantity(int quantityToRemove) {
        quantity -= quantityToRemove;
    }

    @Override
    public String toString() {
        return quantity + " (Price: ₹" + price + " each)";
    }
}

