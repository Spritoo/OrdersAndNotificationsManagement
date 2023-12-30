package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;

public interface ICustomerRepository {

    public boolean add(Customer costumer);
    public void remove(Customer costumer);
    public void updateBalance(Customer costumer, Double balance);
    public boolean addfriend(Credentials credentials, int friend);
    public Customer getCustomerByCredentials(Credentials credentials);
    public Customer getCustomerById(int id);
    public UserInfo getCustomerInfo(Credentials credentials);
    public List<Integer> GetFriends(int id);
    public Customer authenticate(Credentials credentials);
}
