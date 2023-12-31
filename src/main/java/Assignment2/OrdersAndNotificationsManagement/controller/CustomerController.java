package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.dto.AuthenticatedRequest;
import Assignment2.OrdersAndNotificationsManagement.dto.CustomerDTO;
import Assignment2.OrdersAndNotificationsManagement.dto.CustomerFormDTO;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.ICustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> createAccount(@RequestBody CustomerFormDTO customerFormDTO) {
        ResponseEntity<CustomerDTO> response;
        Customer customer = customerService.createAccount(customerFormDTO.getCredentials(), customerFormDTO.getUserInfo());

        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getUserInfo());

            response = ResponseEntity.ok(customerDTO);
        } else {
            response = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return response;
    }

    @PostMapping("/{id}/friends")
    public ResponseEntity<String> addFriend(
            @RequestBody AuthenticatedRequest<Integer> request,
            @PathVariable("id") int id
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());
        int friendId = request.getPayload();

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        if (customer.getId() != id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only add friends to your own account");
        }

        ResponseEntity<String> response;

        if (customerService.addFriend(id, friendId)) {
            response = ResponseEntity.ok("Friend added successfully");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to add friend. The friend ID provided does not exist.");
        }

        return response;
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<Integer>> listFriends(@PathVariable int id) {
        List<Integer> friends = customerService.listFriends(id);

        if (friends == null || friends.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getInfo(@RequestBody Credentials credentials, @PathVariable("id") int id) {
        Customer customer = customerService.authenticate(credentials);

        if (customer != null && customer.getId() == id) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getUserInfo());

            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<String> updateCustomerBalance(
            @RequestBody AuthenticatedRequest<Double> request,
            @PathVariable("id") int id
    ) {
        Customer customer = customerService.authenticate(request.getCredentials());
        double balance = request.getPayload();

        if (customer == null || customer.getId() != id) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        customerService.updateCustomerBalance(customer.getCredentials(),balance);
        return new ResponseEntity<>("Balance updated successfully",HttpStatus.OK);
    }
}
