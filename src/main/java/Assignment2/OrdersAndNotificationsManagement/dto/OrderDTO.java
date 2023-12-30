package Assignment2.OrdersAndNotificationsManagement.dto;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {
    int id;
    // int ownerId;
    double price;
    List<Product> products;
    List<Order> orders;

    public OrderDTO(Order order) {
        this.id = order.getId();
        // this.ownerId = order.getOwner().getId();
        this.price = order.getPrice();
        this.products = order.getProducts();
        this.orders = order.getOrders();
    }

    public int getId() {
        return id;
    }

    /* public int getOwnerId() {
        return ownerId;
    } */

    public double getPrice() {
        return price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
