package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.List;


public interface CustomerService {


    abstract public boolean createAccount(Customer customer);



    public boolean addFriend(int customerId,int friendId) ;


    public List<Product> listAllProductsForCustomer();
}
