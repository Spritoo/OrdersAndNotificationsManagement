package Assignment2.OrdersAndNotificationsManagement.model.Order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements IOrder {
    List<IOrder> orders; //can be either simple order or compound orders
    public CompoundOrder(List<IOrder> orders) {
        this.orders = orders;
    }

    public List<IOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<IOrder> orders) {
        this.orders = orders;
    }
    public void addOrder(IOrder order){
        orders.add(order);
    }
    public void removeOrder(IOrder order){
        orders.remove(order);
    }
    @Override
    public List<Product> getInfo() { // I am 90% sure this will not work
        List<Product> products = new ArrayList<>();

        for (IOrder order: orders) {

            products.addAll(order.getInfo());

        }

        return products;
    }
}
