package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Costumer;
import Assignment2.OrdersAndNotificationsManagement.model.Order.IOrder;

import java.beans.Customizer;
import java.util.HashMap;
import java.util.Map;

public interface ICustomerDB {
    public static Map<Integer, Costumer> customers = new HashMap<Integer, Costumer>();
    public void add(Costumer costumer);
    public void remove(Costumer costumer);
    public void updateBalance(Costumer costumer,Double balance);
    public void addfriend(Costumer me, Costumer friend);
    public Costumer getCustomer(int id);
}
