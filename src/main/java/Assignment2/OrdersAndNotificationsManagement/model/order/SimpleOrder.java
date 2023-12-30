package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.List;

public class SimpleOrder implements IOrder{
    List<Product> productList;
    public int OrderID;
    String simpleOrderOwner;
    SimpleOrder(String costumer){

        simpleOrderOwner = costumer;
    }

    public void addProduct(Product product){

        productList.add(product);
    }

    public List<Product> getInfo(){

        return productList;
    }

    @Override
    public String getOwner() {
        return simpleOrderOwner;
    }

    @Override
    public int getOrderId(){
        return OrderID;
    }

    @Override
    public void setOrderID(int orderId)
    {
        this.OrderID = orderId;
    }
    @Override
    public void setOwner(String owner) {
        simpleOrderOwner = owner;
    }

    public void removeOrder(Product product) {
        productList.remove(product);
    }
}
