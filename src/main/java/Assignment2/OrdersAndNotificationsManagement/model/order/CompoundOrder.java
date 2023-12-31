package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompoundOrder extends Order {
    List<Order> orders; // can be either simple order or compound orders

    public CompoundOrder(Customer owner, List<Order> orders) {
        this.owner = owner;
        this.orders = orders;
    }

    public CompoundOrder(List<Order> orders) {
        this.orders = orders;
    }

    public CompoundOrder() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public boolean hasOrder(Order order){
        return orders.contains(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    @Override
    public Map<Integer, List<Integer>> getOrderData() {
        Map<Integer, List<Integer>> data = null;

        for(Order order : orders) {
            data.putAll(getOrderData());
        }
        return data;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        for (Order order: orders) {
            products.addAll(order.getProducts());
        }

        return products;
    }

    public void addProduct(Product product){
        throw new UnsupportedOperationException("Cannot add products directly to a compound order");
    }

    public void removeProduct(Product product){
        throw new UnsupportedOperationException("Cannot remove products directly from a compound order");
    }
}
