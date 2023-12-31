package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerService ICustomerService;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Customer customer) {
        ResponseEntity<String> response;

        if (ICustomerService.createAccount(customer)) {
            response = ResponseEntity.ok("Account created successfully");
        } else {
            response = ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists");
        }

        return response;
    }

    @PostMapping("/addFriend/{friendId}")
    public ResponseEntity<String> addFriend(@RequestBody Credentials credentials, @PathVariable("friend") int friendId) {
        ResponseEntity<String> response;

        if (ICustomerService.addFriend(credentials, friendId)) {
            response = ResponseEntity.ok("Friend added successfully");
        } else {
            response = ResponseEntity.internalServerError().body("Failed to add friend");
        }

        return response;
    }

    @GetMapping("/listfriends/{id}")
    public ResponseEntity<List<Integer>> listFriends(@PathVariable int id) {
        List<Integer> friends = ICustomerService.listFriends(id);

        if (friends == null || friends.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<UserInfo> getInfo(@RequestBody Credentials credentials) {
        UserInfo userInfo = ICustomerService.getCustomerInfo(credentials);

        if (userInfo != null) {
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updateBalance/{balance}")
    public ResponseEntity<String> updateCustomerBalance(
            @PathVariable double balance,
            @RequestBody Credentials credentials
    ) {
        UserInfo userInfo = ICustomerService.getCustomerInfo(credentials);

        if (userInfo != null) {
            ICustomerService.updateCustomerBalance(credentials,balance);
            return new ResponseEntity<>("Balance updated successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Balance",HttpStatus.UNAUTHORIZED);
        }
    }
}
