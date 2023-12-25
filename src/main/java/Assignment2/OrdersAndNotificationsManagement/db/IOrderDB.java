package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.HashMap;
import java.util.Map;

public interface IOrderDB {
    public static Map<Customer, IOrder> orders = new HashMap<Customer, IOrder>();
    public void add(Customer costumer, IOrder order);
    public void remove(Customer costumer);

}
