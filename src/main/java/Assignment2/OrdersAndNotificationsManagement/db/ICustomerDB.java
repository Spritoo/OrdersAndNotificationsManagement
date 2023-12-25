package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;

public interface ICustomerDB {

    public boolean add(Customer costumer);
    public void remove(Customer costumer);
    public void updateBalance(Customer costumer, Double balance);
    public boolean addfriend(String me, String friend);
    public Customer getCustomer(String email);
    public UserInfo getCustomerInfo(String email, String password);
    public List<String> GetFriends(String email);
}
