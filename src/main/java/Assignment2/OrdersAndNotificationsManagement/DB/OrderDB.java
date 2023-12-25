package Assignment2.OrdersAndNotificationsManagement.DB;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Order.IOrder;

public class OrderDB implements IOrderDB{
    private static OrderDB instance = null;
    public OrderDB getInstance() {
        if(instance == null) {
            instance = new OrderDB();
        }
        return instance;
    }

    @Override
    public void add(Customer costumer, IOrder order) {
        orders.put(costumer,order);
    }

    @Override
    public void remove(Customer costumer) {
        orders.remove(costumer);
    }
}
