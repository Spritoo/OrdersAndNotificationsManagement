package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.DB.CustomerDB;
import Assignment2.OrdersAndNotificationsManagement.DB.ProductDB;
import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.service.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerService {
    private CustomerDB customerDB;
    private ProductDB productDB;
    @PostMapping("/create")
    public Response createAccount(@RequestBody Customer customer){
        Response response = new Response();
        if (customer != null)
        {
            customerDB.add(customer);
            response.setStatus(true);
            response.setMessage("Account created successfully");
        }
        else {
            response.setStatus(false);
            response.setMessage("Failed to create account");
        }
        return response;
    }

    @PostMapping("/{customerId}/add-friends")
    public Response addFriends(@PathVariable("customerId") int customerId, @RequestBody List<Integer> friendIds) {
        Response response = new Response();
        try {
            Customer customer = customerDB.getCustomer(customerId);
            if (customer != null) {
                List<Customer> friendsToAdd = new ArrayList<>();
                for (int friendId : friendIds) {
                    Customer friend = customerDB.getCustomer(friendId);
                    if (friend != null) {
                        friendsToAdd.add(friend);
                    }
                }
                customerDB.addfriend(customer, (Customer) friendsToAdd);
                response.setStatus(true);
                response.setMessage("Friends added successfully");
            } else {
                response.setStatus(false);
                response.setMessage("Customer not found");
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Failed to add friends");
        }
        return response;
    }

    @GetMapping("/{customerId}/list-all-products")
    public ResponseEntity<List<Product>> listAllProductsForCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = customerDB.getCustomer(customerId);
        if (customer != null) {
            List<Product> products = List.of(productDB.getProducts());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return null;
        }
    }
}
