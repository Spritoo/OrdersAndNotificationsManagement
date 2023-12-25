package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.db.CustomerDB;
import Assignment2.OrdersAndNotificationsManagement.db.ProductDB;
import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerDB customerDB;
    private ProductDB productDB;

    CustomerServiceImpl(){
        customerDB = new CustomerDB();
        customerDB = customerDB.getInstance();
        productDB = new ProductDB();
        productDB = productDB.getInstance();
    }
    @Override
    public boolean createAccount(Customer customer){
        //customerDB = customerDB.getInstance();
        if(customerDB.add(customer) & customer != null){
                return true;
        }
        else {
            return false;
        }

    }
    @Override
    public List<Product> listAllProductsForCustomer(){
            List<Product> products = List.of(productDB.getProducts());
            return products;
    }
    @Override
    public boolean addFriend(int customerId,int friendId) {
        Customer c = customerDB.getCustomer(customerId);
        Customer friend = customerDB.getCustomer(friendId);
        if(c != null & friend != null) {
            customerDB.addfriend(c,friend);
            return true;
        } else {
            return false;
        }
    }
}
