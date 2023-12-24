package Assignment2_SE_20216083_20216086_20216005_20216065.OrdersAndNotificationsManagement.model.Order;

import Assignment2_SE_20216083_20216086_20216005_20216065.OrdersAndNotificationsManagement.model.Costumer;
import Assignment2_SE_20216083_20216086_20216005_20216065.OrdersAndNotificationsManagement.model.Product;
import org.springframework.context.annotation.Profile;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
