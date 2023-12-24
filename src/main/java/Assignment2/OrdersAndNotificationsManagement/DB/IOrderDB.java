package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Costumer;
import Assignment2.OrdersAndNotificationsManagement.model.Order.IOrder;
import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.HashMap;
import java.util.Map;

public interface IOrderDB {
    public static Map<Costumer, IOrder> orders = new HashMap<Costumer, IOrder>();
    public void add(Costumer costumer, IOrder order);
    public void remove(Costumer costumer);

}
