package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.order.Order;

import java.util.List;

public interface IOrderRepository {
    public void addOrder(Order order);
    public void removeOrder(int orderId);
    public Order getOrder(int orderId);
    public List<Order> getOrdersByIds(List<Integer> orderIds);
    public List<Order> getOrders();
    public List<Integer> getOrderProducts(int orderId);
}
