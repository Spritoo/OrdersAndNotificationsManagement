package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.db.CustomerDB;
import Assignment2.OrdersAndNotificationsManagement.db.ProductDB;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductDB productDB;

    ProductServiceImpl(){
        productDB = new ProductDB();
        productDB = productDB.getInstance();
    }
    @Override
    public List<Product> listAllProductsForCustomer(){
        List<Product> products = List.of(productDB.getProducts());
        return products;
    }
    public boolean addproducts() { // to test it but how to run this I don't know
        ProductDB.add(new Product(1101,"shampo","koria","healthCare",12.5,10));
        ProductDB.add(new Product(1102,"pizza","china","food",5.25,30));
        ProductDB.add(new Product(1103,"Iphone25","apple","games",600,3));
        return true;
    }
}
