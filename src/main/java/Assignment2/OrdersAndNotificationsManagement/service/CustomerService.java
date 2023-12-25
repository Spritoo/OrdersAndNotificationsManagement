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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;



public interface CustomerService {


    abstract public boolean createAccount(Customer customer);



    public boolean addFriend(int customerId,int friendId) ;


    public List<Product> listAllProductsForCustomer();
}
