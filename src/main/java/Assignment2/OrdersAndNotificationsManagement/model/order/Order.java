package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.Date;
import java.util.List;
import java.util.Map;

// implement an interface?
public abstract class Order {
    protected int id;
    protected Customer owner;

    protected Date creationDate;
    protected Date shippingDate;

    public abstract List<Product> getProducts();
    public abstract void addProduct(Product product);
    public abstract void removeProduct(Product product);

    public abstract List<Order> getOrders();
    public abstract void addOrder(Order order);
    public abstract boolean hasOrder(Order order);
    public abstract void removeOrder(Order order);
    public abstract Map<Integer, List<Integer>> getOrderData();

    public int getId() {
        return id;
    }
    public void setId(int orderId) {
        this.id = orderId;
    }

    public Customer getOwner() {
        return owner;
    }
    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public double getPrice() {
        List<Product> products = getProducts();
        double cost = 0.0;

        for (Product product: products) {
            cost += product.getPrice();
        }

        return cost;
    }
}
