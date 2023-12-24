package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.HashMap;
import java.util.Map;

public interface IProductDB {
    public static Map<Integer, Product> products = new HashMap<Integer, Product>();
    public void add(Product product);
    public Product getProduct(int serial);
    public Product[] getProducts();
    public void updateCount(int count, int serial);
    public boolean checkCount(int costumerWants, int serial);
    public int getProductCount(int serial);
}
