package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.dto.OrderDTO;
import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;
import Assignment2.OrdersAndNotificationsManagement.model.order.SimpleOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.List;
import java.util.Vector;

public interface IOrderService {
    public enum CancellationStatus {
        Success,
        OrderNotFound,
        OrderNotCancellable
    }

    public enum EditStatus {
        Success,
        ParentOrderNotFound,
        ChildOrderNotFound,
        ProductNotFound,
        OrderNotCompound,
        OrderAlreadyContainsOrder,
    }

    SimpleOrder createSimpleOrder(Customer owner);
    CompoundOrder createCompoundOrder(Customer owner, List<Integer> orderIds);
    Order getOrder(int orderId);
    CancellationStatus cancelOrder(int orderId);
    EditStatus addProductToOrder(int order, int productId);
    EditStatus addOrderToOrder(int parentOrderId, int childOrderId);
}
