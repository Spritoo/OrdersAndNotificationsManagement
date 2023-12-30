package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IOrderRepository {
    public void addOrder(IOrder order);
    public IOrder getOrder(int serial);
    public List<IOrder> getOrders();
}
