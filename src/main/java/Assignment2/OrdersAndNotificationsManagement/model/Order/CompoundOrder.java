package Assignment2.OrdersAndNotificationsManagement.model.Order;

import java.util.List;

public class CompoundOrder implements IOrder {
    List<IOrder> orders; //can be other simple order or compound orders
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
    public void displayInfo() {
        for (IOrder order: orders) {
            order.displayInfo();
        }
    }
}
