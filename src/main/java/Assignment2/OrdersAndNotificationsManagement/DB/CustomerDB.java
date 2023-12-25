package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;

public class CustomerDB implements ICustomerDB{
    private static CustomerDB instance = null;
    public CustomerDB getInstance() {
        if(instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }

    @Override
    public void add(Customer costumer) {
        customers.put(costumer.getID(),costumer);
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
        return customers.get(id);
    }
}
