package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

import java.util.List;
import java.util.Vector;

public interface OrderService {

    public boolean addToOrder(Credentials credentials , Vector<Integer> product );
    public List<IOrder> getorderById(int id);
    public List<IOrder> getAllOrders();
}
