package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;

import java.util.HashMap;
import java.util.Map;

public interface ICustomerDB {

    public boolean add(Customer costumer);
    public void remove(Customer costumer);
    public void updateBalance(Customer costumer, Double balance);
    public void addfriend(Customer me, Customer friend);
    public Customer getCustomer(int id);
}
