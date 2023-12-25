package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;

public interface ICustomerDB {

    public boolean add(Customer costumer);
    public void remove(Customer costumer);
    public void updateBalance(Customer costumer, Double balance);
    public void addfriend(Customer me, Customer friend);
    public Customer getCustomer(int id);
}
