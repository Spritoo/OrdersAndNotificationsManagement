package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.List;


public interface CustomerService {


    public boolean createAccount(Customer customer);

    public List<String> listFriends(String email);

    public boolean addFriend(Credentials credentials, String friendId) ;

    public UserInfo getCustomerInfo(Credentials credentials);

    public boolean addProductToOrder(Credentials credentials, int productID);

}
