package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.List;

public class SimpleOrder implements IOrder{
    List<Product> productList;

    Customer simpleOrderOwner;
    SimpleOrder(Customer costumer){

        simpleOrderOwner = costumer;
    }

    public void addProduct(Product product){

        productList.add(product);
    }

    public List<Product> getInfo(){

        return productList;
    }

    public void removeOrder(Product product) {
        productList.remove(product);
    }
}
