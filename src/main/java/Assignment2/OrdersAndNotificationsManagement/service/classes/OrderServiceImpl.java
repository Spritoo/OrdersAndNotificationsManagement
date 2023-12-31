package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;
import Assignment2.OrdersAndNotificationsManagement.model.order.SimpleOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.repository.classess.OrderRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classess.ProductRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.IOrderRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.IProductRepository;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    private static final int MAX_ORDER_CANCEL_TIME_IN_DAYS = 2;
    private static final int MAX_SHIPMENT_CANCEL_TIME_IN_DAYS = 2;

    IOrderRepository orderRepository = OrderRepository.getInstance();
    IProductRepository productRepository = ProductRepository.getInstance();

    public SimpleOrder createSimpleOrder(Customer owner) {
        SimpleOrder order = new SimpleOrder(owner);

        orderRepository.addOrder(order);

        return order;
    }

    @Override
    public CompoundOrder createCompoundOrder(Customer owner, List<Integer> orderIds) {
        List<Order> orders = orderRepository.getOrdersByIds(orderIds);

        if (orders.size() < orderIds.size()) {
            return null;
        }

        CompoundOrder order = new CompoundOrder(owner, orders);

        orderRepository.addOrder(order);

        return order;
    }

    @Override
    public Order getOrder(int orderId) {
        return orderRepository.getOrder(orderId);
    }

    @Override
    public void cancelOrder(int orderId) {
        orderRepository.removeOrder(orderId);
    }

    @Override
    public CancellationStatus canCancelOrder(int orderId) {
        Order order = getOrder(orderId);

        if (order == null) {
            return CancellationStatus.OrderNotFound;
        }

        Date creationDate = order.getShippingDate();
        Date currentDate = new Date();

        // get difference in days between two dates
        long diffInDays = ChronoUnit.DAYS.between(creationDate.toInstant(), currentDate.toInstant());

        if (diffInDays > MAX_ORDER_CANCEL_TIME_IN_DAYS) {
            return CancellationStatus.OrderNotCancellable;
        }

        return CancellationStatus.Success;
    }

    @Override
    public EditStatus addProductToOrder(int orderId, int productId) {
        Order order = getOrder(orderId);

        if (order == null) {
            return EditStatus.ParentOrderNotFound;
        }

        Product product = productRepository.getProduct(productId);

        if (product == null) {
            return EditStatus.ProductNotFound;
        }

        try {
            order.addProduct(product);
            product.decrementCount();
        } catch (UnsupportedOperationException e) {
            return EditStatus.OrderNotCompound;
        }

        return EditStatus.Success;
    }

    @Override
    public EditStatus addOrderToOrder(int parentOrderId, int childOrderId) {
        Order order = getOrder(parentOrderId);

        if (order == null) {
            return EditStatus.ParentOrderNotFound;
        }

        Order childOrder = getOrder(childOrderId);

        if (childOrder == null) {
            return EditStatus.ChildOrderNotFound;
        }

        if (order.hasOrder(childOrder)) {
            return EditStatus.OrderAlreadyContainsOrder;
        }

        try {
            order.addOrder(childOrder);
        } catch (UnsupportedOperationException e) {
            return EditStatus.OrderNotCompound;
        }

        return EditStatus.Success;
    }

    public ShipmentStatus shipOrder(int orderId) {
        Order order = getOrder(orderId);

        if (order == null) {
            return ShipmentStatus.ShipmentNotFound;
        }

        Date currentDate = new Date();
        order.setShippingDate(currentDate);

        return ShipmentStatus.Success;
    }

    public ShipmentStatus cancelShipment(int orderId) {
        Order order = getOrder(orderId);

        if (order == null) {
            return ShipmentStatus.ShipmentNotFound;
        }

        Date creationDate = order.getShippingDate();
        Date currentDate = new Date();

        // get difference in days between two dates
        long diffInDays = ChronoUnit.DAYS.between(creationDate.toInstant(), currentDate.toInstant());

        if (diffInDays > MAX_SHIPMENT_CANCEL_TIME_IN_DAYS) {
            return ShipmentStatus.ShipmentNotCancellable;
        }

        return ShipmentStatus.Success;
    }

    @Override
    public Map<Integer, List<Integer>> getOrderData(int orderID) {
        return orderRepository.getOrder(orderID).getOrderData();
    }

    /* private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    OrderServiceImpl(){
        customerRepository = CustomerRepository.getInstance();
        productRepository = ProductRepository.getInstance();
    }

    public boolean addToOrder(Credentials credentials , Vector<Integer> product ) { //productId and count after it {21665,5}
        Customer customer = customerRepository.getCustomer(credentials.getEmail());

        if(customer != null && credentials.getPassword().equals(customer.getCredentials().getPassword())) {
            // to check if the product ID and count are available
            for(int i=0;i < product.size();i++) {
                if (productRepository.getProduct(product.get(i)) != null) {
                    i++;
                    if(!productRepository.checkCount(product.get(i),product.get(i-1))) {
                        return false;
                    }
                }
            }

            SimpleOrder order = new SimpleOrder();
            for(int i=0;i < product.size();i++) {
                order.
            }
        }
        else
        {
            CompoundOrder order = new CompoundOrder();

        }

    }

    @Override
    public List<Order> getOrderById(int id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    } */
}
