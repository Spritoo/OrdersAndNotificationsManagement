package Assignment2.OrdersAndNotificationsManagement.service.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;


public interface ICustomerService {
    public boolean createAccount(Customer customer);
    public List<Integer> listFriends(int Id);
    public boolean addFriend(Credentials credentials, int friendId) ;
    public UserInfo getCustomerInfo(Credentials credentials);
    public Customer authenticate(Credentials credentials);
}
