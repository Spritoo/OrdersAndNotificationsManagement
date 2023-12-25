package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductDB {
    private static Map<Integer, Product> products = new HashMap<Integer, Product>();
    private static ProductDB instance = null;
    public ProductDB getInstance(){
        if(instance == null){
            instance = new ProductDB();
        }
        return instance;
    }
    public static void add(Product product) {
        products.put(product.getSerialNumber(),product);
    }

    public Product getProduct(int serial) {
        return products.get(serial);
    }

    public Product[] getProducts() {
        try {
            Set<Integer> ids = products.keySet();
            Product[] productsArry = new Product[ids.size()];
            int i=0;
            for(Integer id : ids){
                productsArry[i] = products.get(id);
                i++;
            }
            return productsArry;
        } catch (Exception e) {
            System.out.println("Exception in getAllPersons as" + e.getMessage());
        }
        return null;
    }

    public void updateCount(int count, int serial) { // update the new count
        products.get(serial).setCount(count);
    }

    public boolean checkCount(int costumerWants, int serial) { // checks if we have the amount the costumer wants
        if(products.get(serial).getCount() >= costumerWants)
            return true;
        return false;
    }

    public int getProductCount(int serial) {
        return products.get(serial).getCount();
    }


}
