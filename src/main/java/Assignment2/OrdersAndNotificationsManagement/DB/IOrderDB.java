package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Order.IOrder;

import java.util.HashMap;
import java.util.Map;

public interface IOrderDB {
    public static Map<Customer, IOrder> orders = new HashMap<Customer, IOrder>();
    public void add(Customer costumer, IOrder order);
    public void remove(Customer costumer);

}