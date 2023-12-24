package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Costumer;

public class CustomerDB implements ICustomerDB{
    private static CustomerDB instance = null;
    public CustomerDB getInstance() {
        if(instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }

    @Override
    public void add(Costumer costumer) {
        customers.put(costumer.getID(),costumer);
    }

    @Override
    public void remove(Costumer costumer) {
        customers.remove(costumer.getID());
    }

    @Override
    public void updateBalance(Costumer costumer, Double balance) {
        customers.get(costumer.getID()).setBalance(balance);
    }

    @Override
    public void addfriend(Costumer me, Costumer friend) {
        customers.get(me.getID()).addFriends(friend);
    }

    @Override
    public Costumer getCustomer(int id) {
        return customers.get(id);
    }
}
