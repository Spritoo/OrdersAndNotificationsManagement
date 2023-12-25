package Assignment2.OrdersAndNotificationsManagement.db;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class CustomerDB implements ICustomerDB{
    private static CustomerDB instance = null;
    private static Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
    public CustomerDB getInstance() {
        if(instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }

    @Override
    public boolean add(Customer costumer) {
        if(customers.containsKey(costumer.getID())){
            return false;
        }
        else {
            customers.put(costumer.getID(),costumer);
            return true;
        }

    }

    @Override
    public void remove(Customer costumer) {
        customers.remove(costumer.getID());
    }

    @Override
    public void updateBalance(Customer costumer, Double balance) {
        customers.get(costumer.getID()).setBalance(balance);
    }

    @Override
    public void addfriend(Customer me, Customer friend) {
        customers.get(me.getID()).addFriends(friend);
    }

    @Override
    public Customer getCustomer(int id) {
        if(customers.containsKey(id)){
            return customers.get(id);
        } else {
            return null;
        }
    }
    @Override
    public List<Customer> GetFriends(int id)//to do
    {
        if(customers.containsKey(id))
        {
            return customers.get(id).getFriends();
        }
        return null;
    }
    @Override
    public List<Customer> getallCustomer()//to do
    {
        Iterator<Map.Entry<Integer, Customer>> iterator = customers.entrySet().iterator();
        List<Customer> allFriends = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Customer> entry = iterator.next();
            allFriends.addAll(entry.getValue().getFriends());
        }
        return allFriends;
    }
}
