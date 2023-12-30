package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.List;

public interface IOrder {
    public List<Product> getInfo();
    public String getOwner();
    public void setOwner(String owner);
    public int getOrderId();
    public void setOrderID(int orderId);
}
