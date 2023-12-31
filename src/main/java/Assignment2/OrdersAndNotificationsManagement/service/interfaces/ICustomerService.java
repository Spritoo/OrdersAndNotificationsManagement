package Assignment2.OrdersAndNotificationsManagement.service.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;
import java.util.Map;


public interface ICustomerService {
    public boolean createAccount(Customer customer);
    public List<Integer> listFriends(int Id);
    public boolean addFriend(Credentials credentials, int friendId) ;
    public UserInfo getCustomerInfo(Credentials credentials);
    public Customer authenticate(Credentials credentials);
    public boolean correctBalance(Credentials credentials, int productID);
    public void returnBalanceAfterCancellation(Map<Integer, List<Integer>> customerAndProducts);
    public boolean isFriends(int id, int friend);
    public void returnShipmentFees(Map<Integer, List<Integer>> customerAndProducts);
    public boolean decductShipmentFees(Map<Integer, List<Integer>> customerAndProducts);
    public boolean updateCustomerBalance(Credentials credentials,double newBalance);
}
