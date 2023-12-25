package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class CustomerDB implements ICustomerDB{
    private static CustomerDB instance = null;
    private static Map<String, Customer> customers = new HashMap<String, Customer>();
    public CustomerDB getInstance() {
        if(instance == null) {
            instance = new CustomerDB();
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
    public void addfriend(Customer me, Customer friend) {
        customers.get(me.getUserInfo().getEmail()).addFriends(friend);
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
        if(customers.containsKey(email)){
            return customers.get(email);
        } else {
            return null;
        }
    }

    @Override
    public List<UserInfo> GetFriends(String email)//to do
    {
        List<UserInfo> friendsInfo = new ArrayList<>();
        if(customers.containsKey(email))
        {
            for(Customer c : customers.get(email).getFriends()) {
                friendsInfo.add(c.getUserInfo());
            }
            return friendsInfo;
        }
        return null;
    }
    @Override
    public List<Customer> getallCustomer()//to do
    {
        Iterator<Map.Entry<String, Customer>> iterator = customers.entrySet().iterator();
        List<Customer> allFriends = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Customer> entry = iterator.next();
            allFriends.addAll(entry.getValue().getFriends());
        }
        return allFriends;
    }
}
