package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.db.CustomerDB;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

public class OrderServiceImpl implements OrderService{
    private CustomerDB customerDB;
    OrderServiceImpl(){
        customerDB = new CustomerDB();
        customerDB = customerDB.getInstance();
    }
    public void addToOrder(Credentials credentials , int productSerialNum){

    }
}
