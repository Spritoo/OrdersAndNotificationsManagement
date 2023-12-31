package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder extends Order {
    private List<Product> productList = new ArrayList<>();


    public SimpleOrder(Customer owner) {
        this.owner = owner;
    }

    public SimpleOrder() {}

    @Override
    public void addProduct(Product product){
        productList.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProducts() {
        return productList;
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public void addOrder(Order order) {
        throw new UnsupportedOperationException("Cannot add orders to a simple order");
    }

    public boolean hasOrder(Order order) {
        return false;
    }

    @Override
    public void removeOrder(Order order) {
        throw new UnsupportedOperationException("Cannot remove orders from a simple order");
    }
}
