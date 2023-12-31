package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.HashMap;
import java.util.Map;

public interface IProductRepository {
    public static Map<Integer, Product> products = new HashMap<Integer, Product>();
    public void addProduct(Product product);
    public Product getProduct(int serial);
    public Product[] getProducts();
    public void updateCount(int count, int serial);
    public boolean checkCount(int costumerWants, int serial);
    public int getProductCount(int serial);
}
