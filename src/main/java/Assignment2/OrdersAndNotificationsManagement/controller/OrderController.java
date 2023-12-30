package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.dto.AuthenticatedRequest;
import Assignment2.OrdersAndNotificationsManagement.dto.OrderDTO;
import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;
import Assignment2.OrdersAndNotificationsManagement.model.order.SimpleOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.service.ICustomerService;
import Assignment2.OrdersAndNotificationsManagement.service.IOrderService;
import org.apache.coyote.Response;
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
        CompoundOrder order = orderService.createCompoundOrder(customer, orderIds);

        if (order == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new OrderDTO(order));
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

    @PostMapping("/{orderId}/products/{productId}")
    ResponseEntity<String> addProductToOrder(
            @PathVariable("orderId") int orderId,
            @PathVariable("productId") int productId,
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

        if (order.getOwner().getId() != customer.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this order");
        }

        IOrderService.EditStatus status = orderService.addProductToOrder(orderId, productId);

        return switch (status) {
            case Success -> ResponseEntity.ok("Product added to order successfully");
            case ProductNotFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            case OrderNotCompound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not compound");
            default -> null;
        };
    }

    @PostMapping("/{parentOrderId}/orders/{childOrderId}")
    ResponseEntity<String> addOrderToOrder(
            @PathVariable("parentOrderId") int parentOrderId,
            @PathVariable("childOrderId") int childOrderId,
            @RequestBody AuthenticatedRequest<Void> request
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Order parentOrder = orderService.getOrder(parentOrderId);

        if (parentOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent order not found");
        }

        if (parentOrder.getOwner().getId() != customer.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this order");
        }

        // todo: check if they are both friends

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

        IOrderService.CancellationStatus status = orderService.cancelOrder(orderId);

        return switch (status) {
            case Success -> ResponseEntity.ok("Order cancelled successfully");
            case OrderNotFound -> ResponseEntity.status(404).body("Order not found");
            case OrderNotCancellable -> ResponseEntity.status(403).body("The cancellation period for this order has expired");
            default -> null;
        };
    }

    // todo: this
    // DELETE /orders/{id}/shipment
    /*
    @DeleteMapping("/{id}/shipment")
    Response cancelShipment(@PathVariable("id") int id) {
        Response response = new Response();
        IOrderService.CancellationStatus status = orderService.cancelShipment(id);

        if (status == IOrderService.CancellationStatus.Success) {
            response.setStatus(true);
            response.setMessage("Shipment cancelled successfully");
        } else {
            response.setStatus(false);

            if (status == IOrderService.CancellationStatus.OrderNotFound) {
                response.setMessage("Order not found");
            } else if (status == IOrderService.CancellationStatus.OrderNotCancellable) {
                response.setMessage("The cancellation period for this shipment has expired");
            }
        }

        return response;
    }

    @PostMapping("/{id}/shipment")
    Response shipOrder(@PathVariable("id") int id) {
        Response response = new Response();
        IOrderService.ShipmentStatus status = orderService.shipOrder(id);

        if (status == IOrderService.ShipmentStatus.Success) {
            response.setStatus(true);
            response.setMessage("Order shipped successfully");
        } else {
            response.setStatus(false);

            if (status == IOrderService.ShipmentStatus.OrderNotFound) {
                response.setMessage("Order not found");
            } else if (status == IOrderService.ShipmentStatus.OrderNotShippable) {
                response.setMessage("Order is not shippable");
            }
        }

        return response;
    }
    */
}
