package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

public interface OrderService {

    public void addToOrder(Credentials credentials , int productSerialNum);
    
}
