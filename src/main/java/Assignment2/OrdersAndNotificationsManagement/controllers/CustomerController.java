package Assignment2.OrdersAndNotificationsManagement.controllers;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
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
    public Response createAccount(@RequestBody Customer customer) {
        Response response = new Response();
        if (customerService.createAccount(customer)) {
            response.setStatus(true);
            response.setMessage("account added");
        } else {
            response.setStatus(false);
            response.setMessage("error making account");
        }
        return response;
    }

    @PostMapping("/addFriend/{friend}")
    public Response addFriend(@RequestBody Credentials credentials, @PathVariable("friend") String friend) {
        Response response = new Response();
        if (customerService.addFriend(credentials, friend)) {
            response.setStatus(true);
            response.setMessage("Friend added successfully");
        } else {
            response.setStatus(false);
            response.setMessage("Failed to add friend");
        }
        return response;
    }

    @GetMapping("/listfriends/{email}")
    public ResponseEntity<List<String>> listfriends(@PathVariable String email) {
        List<String> friends = customerService.listfriends(email);

        if (friends == null || friends.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }



    @GetMapping("/check")
    public ResponseEntity<UserInfo> getInfo(@RequestBody Credentials credentials) {
        UserInfo userInfo = customerService.getCustomerInfo(credentials.getEmail(), credentials.getPassword());
        if (userInfo != null) {
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
