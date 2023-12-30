package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;

public interface ICustomerRepository {

    public boolean add(Customer costumer);
    public void remove(Customer costumer);
    public void updateBalance(Customer costumer, Double balance);
    public boolean addfriend(String me, String friend);
    public Customer getCustomer(String email);
    public UserInfo getCustomerInfo(Credentials credentials);
    public List<String> GetFriends(String email);
}
