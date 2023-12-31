package Assignment2.OrdersAndNotificationsManagement.repository.classes;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.ICustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    private static CustomerRepository instance = null;
    private Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
    public int nextId = 1;

    private CustomerRepository() {}

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }

        return instance;
    }

    @Override
    public Customer add(Credentials credentials, UserInfo userInfo) {
        Customer existingCustomer = getCustomerByEmail(credentials.getEmail());

        if (existingCustomer != null) {
            return null;
        }

        int id = nextId++;
        Customer customer = new Customer(id, credentials, userInfo);

        customers.put(id, customer);

        return customer;
    }

    @Override
    public void remove(Customer costumer) {
        customers.remove(costumer.getId());
    }

    @Override
    public void updateBalance(Customer customer, Double balance) {
        customers.get(customer.getId()).getUserInfo().setBalance(balance);
    }

    @Override
    public boolean checkBalance(Credentials credentials, Double price) {
        Customer customer = authenticate(credentials);

        return customer.getUserInfo().getBalance() > price;
    }

    @Override
    public void deductBalance(Credentials credentials, Double price) {
        Customer customer = authenticate(credentials);

        customers.get(customer.getId()).getUserInfo().setBalance(customer.getUserInfo().getBalance() - price);
    }

    @Override
    public void addFriend(Customer customer, Customer friend) {
        int myId = customer.getId();
        int friendId = friend.getId();

        List<Integer> myFriends = customer.getFriends();
        List<Integer> theirFriends = friend.getFriends();

        if (!myFriends.contains(friendId)){
            myFriends.add(friendId);
        }

        if (!theirFriends.contains(myId)){
            theirFriends.add(myId);
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
    public Customer getCustomerByEmail(String emailAddress) {
        for (Customer customer: customers.values()) {
            if (customer.getCredentials().getEmail().equals(emailAddress)) {
                return customer;
            }
        }

        return null;
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

    @Override
    public boolean isFriends(int id, int friend) {
        Customer customer = getCustomerById(id);
        List<Integer> friends = customer.getFriends();

        for(int friendID : friends) {
            if(friendID == friend)
                return true;
        }

        return false;
    }
}
