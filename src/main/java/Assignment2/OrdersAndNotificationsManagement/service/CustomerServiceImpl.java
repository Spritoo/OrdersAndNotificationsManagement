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


    CustomerServiceImpl(){
        customerDB = new CustomerDB();
        customerDB = customerDB.getInstance();
    }
    @Override
    public boolean createAccount(Customer customer){
        if(customerDB.add(customer) & customer != null){
                return true;
        }
        else {
            return false;
        }

    }
    @Override
    public List<Customer> listfriends()//to doo
    {
        return null;
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
