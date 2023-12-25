package Assignment2.OrdersAndNotificationsManagement.controllers;

import Assignment2.OrdersAndNotificationsManagement.db.ProductDB;
import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.service.CustomerService;
import Assignment2.OrdersAndNotificationsManagement.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public Response createAccount(@RequestBody Customer customer){
        Response response = new Response();
        if(customerService.createAccount(customer)){
            response.setStatus(true);
            response.setMessage("account added");
        } else {
            response.setStatus(false);
            response.setMessage("error making account");
        }
        return response;
    }
    @GetMapping("/listCustomer")
    public ResponseEntity<Customer> listCustomer(@RequestBody int id){
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/listfriends")
    public ResponseEntity<List<Customer>> listfriends(@RequestBody int id){
        return new ResponseEntity<>(customerService.listfriends(id), HttpStatus.OK);
    }
    
}
