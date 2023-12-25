package Assignment2.OrdersAndNotificationsManagement.Controllers;

import Assignment2.OrdersAndNotificationsManagement.model.Customer;
import Assignment2.OrdersAndNotificationsManagement.service.CustomerService;
import Assignment2.OrdersAndNotificationsManagement.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
