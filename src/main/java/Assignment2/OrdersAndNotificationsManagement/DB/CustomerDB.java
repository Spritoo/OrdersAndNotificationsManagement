package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;

import java.util.HashMap;
import java.util.Map;

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
}
