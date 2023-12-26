package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/list")
    public ResponseEntity<List<Product>> listAllProductsForCustomer() {
        List<Product> products = productService.listAllProductsForCustomer();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/add")
    public boolean addlistproducts(){
        return productService.addproducts();
    }
}
