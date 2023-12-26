package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.repository.CustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

public class OrderServiceImpl implements OrderService{
    private CustomerRepository customerDB;

    OrderServiceImpl(){
        customerDB = customerDB.getInstance();
    }

    public void addToOrder(Credentials credentials , int productSerialNum){

    }
}
