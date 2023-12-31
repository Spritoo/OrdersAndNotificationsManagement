package Assignment2.OrdersAndNotificationsManagement.repository.classess;

import Assignment2.OrdersAndNotificationsManagement.model.order.Order;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.IOrderRepository;

import java.util.*;

public class OrderRepository implements IOrderRepository {
    private static OrderRepository instance = null;
    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }

        return instance;
    }

    @Override
    public void addOrder(Order order) {
        int orderId = getNextOrderId();

        order.setId(orderId);
        orders.put(orderId, order);
    }

    @Override
    public void removeOrder(int orderId) {
        orders.remove(orderId);
    }

    @Override
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    private int getNextOrderId() {
        return nextId++;
    }

    public List<Order> getOrdersByIds(List<Integer> orderIds) {
        List<Order> orders = new ArrayList<>();

        for (int orderId: orderIds) {
            orders.add(getOrder(orderId));
        }

        return orders;
    }
}
