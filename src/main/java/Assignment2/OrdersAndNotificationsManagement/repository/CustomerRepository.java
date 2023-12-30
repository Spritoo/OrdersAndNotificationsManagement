package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    private static CustomerRepository instance = null;
    private Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

    private CustomerRepository() {}

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }

        return instance;
    }

    @Override
    public boolean add(Customer costumer) {
        if(customers.containsKey(costumer.getId())){
            return false;
        }
        else {
            customers.put(costumer.getId(),costumer);
            return true;
        }

    }

    @Override
    public void remove(Customer costumer) {
        customers.remove(costumer.getId());
    }

    @Override
    public void updateBalance(Customer costumer, Double balance) {
        customers.get(costumer.getId()).getUserInfo().setBalance(balance);
    }

    @Override
    public boolean addfriend(Credentials credentials, int friend) {
        Customer customer = authenticate(credentials);
        if(customer.getFriends().contains(friend)){
            return false;
        } else {
            int id = customer.getId();
            customers.get(id).addFriend(friend);
            customers.get(friend).addFriend(id);
            return true;
        }
    }

    @Override
    public UserInfo getCustomerInfo(Credentials credentials) {
        return authenticate(credentials).getUserInfo();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public Customer getCustomerByCredentials(Credentials credentials) {
        return authenticate(credentials);
    }

    @Override
    public List<Integer> GetFriends(int id) {
        Customer customer = customers.get(id);
        if (customer != null) {
            List<Integer> friends = customer.getFriends();
            return friends != null ? friends : new ArrayList<>();
        } else {
            // Handle the case where the customer is not found
            return new ArrayList<>();
        }
    }

    @Override
    public Customer authenticate(Credentials credentials) {
        for (Customer customer: customers.values()) {
            Credentials customerCredentials = customer.getCredentials();

            boolean isRightEmail = customerCredentials.getEmail().equals(credentials.getEmail());
            boolean isRightPassword = customerCredentials.getPassword().equals(credentials.getPassword());

            if (isRightEmail && isRightPassword) {
                return customer;
            }
        }

        return null;
    }

}
