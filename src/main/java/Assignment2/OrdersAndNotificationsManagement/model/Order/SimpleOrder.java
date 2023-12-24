package Assignment2.OrdersAndNotificationsManagement.model.Order;

import Assignment2.OrdersAndNotificationsManagement.model.Costumer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.List;

public class SimpleOrder implements IOrder{
    List<Product> productList;

    Costumer simpleOrderOwner;
    SimpleOrder(Costumer costumer){
        simpleOrderOwner = costumer;
    }
    public void addProduct(Product product){
        productList.add(product);
    }
    public void displayInfo(){
        for (Product product: productList) {
            product.printInfo();
        }
    }
}
