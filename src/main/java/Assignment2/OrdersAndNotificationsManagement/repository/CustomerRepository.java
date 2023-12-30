package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    private static CustomerRepository instance = null;
    private Map<String, Customer> customers = new HashMap<String, Customer>();

    private CustomerRepository() {}

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }

        return instance;
    }

    @Override
    public boolean add(Customer costumer) {
        if(customers.containsKey(costumer.getUserInfo().getEmail())){
            return false;
        }
        else {
            customers.put(costumer.getUserInfo().getEmail(),costumer);
            return true;
        }

    }

    @Override
    public void remove(Customer costumer) {
        customers.remove(costumer.getUserInfo().getEmail());
    }

    @Override
    public void updateBalance(Customer costumer, Double balance) {
        customers.get(costumer.getUserInfo().getEmail()).getUserInfo().setBalance(balance);
    }

    @Override
    public boolean addfriend(String me, String friend) {
        if(customers.get(me).getFriends().contains(friend)){
            return false;
        } else {
            customers.get(me).addFriend(friend);
            customers.get(friend).addFriend(me);
            return true;
        }
    }

    @Override
    public UserInfo getCustomerInfo(String email, String password) {
        if(customers.containsKey(email)){
            if(password.equals( customers.get(email).getCredentials().getPassword()))
                return customers.get(email).getUserInfo();

        }
        return null;
    }
    public Customer getCustomer(String email) {
        return customers.get(email);
    }

    @Override
    public List<String> GetFriends(String email) {
        Customer customer = customers.get(email);
        if (customer != null) {
            List<String> friends = customer.getFriends();
            return friends != null ? friends : new ArrayList<>();
        } else {
            // Handle the case where the customer is not found
            return new ArrayList<>();
        }
    }


}