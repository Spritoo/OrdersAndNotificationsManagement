package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

import java.util.Vector;

public interface OrderService {

    public boolean addToOrder(Credentials credentials , Vector<Integer> product );

}
