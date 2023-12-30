package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.*;

public class OrderRepository implements IOrderRepository{
    private static OrderRepository instance = null;
    private Map<Integer, IOrder> Order = new HashMap<Integer, IOrder>();
    private Random random = new Random();
    public OrderRepository getInstance() {
        if(instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    @Override
    public void addOrder(IOrder order) {
        int orderId = generateRandomOrderId();
        //order.setOrderId(orderId);
        //orders.put(orderId, order);
    }

    @Override
    public IOrder getOrder(int OrderID) {
        return Order.get(OrderID);
    }

    @Override
    public List<IOrder> getOrders() {
        return new ArrayList<>(Order.);
    }
    private int generateRandomOrderId() {
        // Generate a random integer between 10,000,000 and 99,999,999
        return 10000000 + random.nextInt(90000000);
    }
}
