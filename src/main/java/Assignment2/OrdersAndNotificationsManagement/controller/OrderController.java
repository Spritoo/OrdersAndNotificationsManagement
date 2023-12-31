package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.dto.AuthenticatedRequest;
import Assignment2.OrdersAndNotificationsManagement.dto.OrderDTO;
import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;
import Assignment2.OrdersAndNotificationsManagement.model.order.SimpleOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.ICustomerService;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.IOrderService;
import Assignment2.OrdersAndNotificationsManagement.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: Abstract controller with authentication method OR use spring security OR a middleware
// The authentication is fucked please someone come up with a better solution

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    ICustomerService customerService;

    // POST /orders/create/simple
    @PostMapping("/create/simple")
    ResponseEntity<OrderDTO> createSimpleOrder(@RequestBody AuthenticatedRequest<Void> request) {
        Customer customer = customerService.authenticate(request.getCredentials());

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SimpleOrder order = orderService.createSimpleOrder(customer);

        return ResponseEntity.ok(new OrderDTO(order));
    }

    // POST /orders/create/compound
    @PostMapping("/create/compound")
    ResponseEntity<OrderDTO> createCompoundOrder(@RequestBody AuthenticatedRequest<List<Integer>> request) {
        Customer customer = customerService.authenticate(request.getCredentials());

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Integer> orderIds = request.getPayload();
        Pair<IOrderService.CreateCompoundOrderStatus, CompoundOrder> result = orderService.createCompoundOrder(customer, orderIds);

        return switch (result.getKey()) {
            case Success -> ResponseEntity.ok(new OrderDTO(result.getValue()));
            case OrderNotFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            case OrderOwnerNotFriend -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            default -> null;
        };
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    ResponseEntity<OrderDTO> getOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrder(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new OrderDTO(order));
    }

    // POST /orders/{orderId}/products/{productId}
    @PostMapping("/{orderId}/products")
    ResponseEntity<String> addProductToOrder(
            @PathVariable("orderId") int orderId,
            @RequestBody AuthenticatedRequest<Integer> request
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());
        int productId = request.getPayload();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Order order = orderService.getOrder(orderId);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        if (order.getOwner().getId() != customer.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this order");
        }

        if(!customerService.correctBalance(request.getCredentials(), productId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You do not have enough balance");
        }

        IOrderService.EditStatus status = orderService.addProductToOrder(orderId, productId);

        return switch (status) {
            case Success -> ResponseEntity.ok("Product added to order successfully");
            case ProductNotFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            case OrderNotCompound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not compound");
            default -> null;
        };
    }

    // POST /order/{parentOrderId}/orders/{childOrderId}
    @PostMapping("/{parentOrderId}/orders")
    ResponseEntity<String> addOrderToOrder(
            @PathVariable("parentOrderId") int parentOrderId,
            @RequestBody AuthenticatedRequest<Integer> request
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());
        int childOrderId = request.getPayload();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Order parentOrder = orderService.getOrder(parentOrderId);
        Order childOrder = orderService.getOrder(childOrderId);

        if (parentOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent order not found");
        }

        if (parentOrder.getOwner().getId() != customer.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this order");
        }

        // todo: check if they are both friends
        if(!customerService.isFriends(parentOrder.getOwner().getId(),childOrder.getOwner().getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        IOrderService.EditStatus status = orderService.addOrderToOrder(parentOrderId, childOrderId);

        return switch (status) {
            case Success -> ResponseEntity.ok("Order added to order successfully");
            case ChildOrderNotFound -> ResponseEntity.status(404).body("Child order not found");
            case OrderNotCompound -> ResponseEntity.status(403).body("Order is not compound");
            default -> null;
        };
    }

    // DELETE /orders/{id}
    @DeleteMapping("/{id}")
    ResponseEntity<String> cancelOrder(
            @PathVariable("id") int orderId,
            @RequestBody AuthenticatedRequest<Void> request
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Order order = orderService.getOrder(orderId);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        IOrderService.CancellationStatus status = orderService.canCancelOrder(orderId);

        if(status == IOrderService.CancellationStatus.Success) {
            customerService.returnBalanceAfterCancellation(orderService.getOrderData(orderId));
            orderService.cancelOrder(orderId);
        }

        return switch (status) {
            case Success -> ResponseEntity.ok("Order cancelled successfully");
            case OrderNotFound -> ResponseEntity.status(404).body("Order not found");
            case OrderNotCancellable -> ResponseEntity.status(403).body("The cancellation period for this order has expired");
            default -> null;
        };
    }

    // todo: this
    // DELETE /orders/{id}/shipment
    @DeleteMapping("/{id}/shipment")
    ResponseEntity<String> cancelShipment(@PathVariable("id") int id) {
        Order order = orderService.getOrder(id);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        IOrderService.ShipmentStatus status = orderService.cancelShipment(id);

        if(status == IOrderService.ShipmentStatus.Success) {
            customerService.returnShipmentFees(orderService.getOrderData(id));
            orderService.cancelShipment(id);
        }
        else {
            status = IOrderService.ShipmentStatus.ShipmentNotCancellable;
        }

        return switch (status) {
            case Success -> ResponseEntity.ok("Shipment cancelled successfully");
            case ShipmentNotCancellable -> ResponseEntity.status(403).body("Shipment can not be cancelled");
            default -> null;
        };
    }

    // POST /orders/{id}/shipment
    @PostMapping("/{id}/shipment")
    ResponseEntity<String> shipOrder(@PathVariable("id") int id) {
        Order order = orderService.getOrder(id);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }

        IOrderService.ShipmentStatus status = orderService.shipOrder(id);


        if(!customerService.decductShipmentFees(orderService.getOrderData(id))) {
            status = IOrderService.ShipmentStatus.ShipmentCancelled;
        }


        return switch (status) {
            case Success -> ResponseEntity.status(HttpStatus.OK).body("Order shipped successfully");
            case ShipmentNotFound -> ResponseEntity.status(404).body("Order not found");
            case ShipmentCancelled -> ResponseEntity.status(403).body("A customer does not have balance for shipment");
            default -> null;
        };
    }
}
