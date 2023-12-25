package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.db.CustomerDB;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
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
    public List<UserInfo> listfriends(String email)
    {
        return customerDB.GetFriends(email);
    }
    @Override
    public boolean addFriend(String customerEmail, String friendEmail) {
        Customer c = customerDB.getCustomer(customerEmail);
        Customer friend = customerDB.getCustomer(friendEmail);
        if(c != null & friend != null) {
            customerDB.addfriend(c,friend);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserInfo getCustomerInfo(String email, String password) {
        return customerDB.getCustomerInfo(email,password);
    }
}
