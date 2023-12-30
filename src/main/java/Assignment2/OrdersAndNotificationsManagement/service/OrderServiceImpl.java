package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.SimpleOrder;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.repository.CustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.repository.ProductRepository;

import java.util.Vector;

public class OrderServiceImpl implements OrderService{
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    OrderServiceImpl(){
        customerRepository = CustomerRepository.getInstance();
        productRepository = ProductRepository.getInstance();
    }

    public boolean addToOrder(Credentials credentials , Vector<Integer> product ) { //productId and count after it {21665,5}
        Customer customer = customerRepository.getCustomer(credentials.getEmail());

        if(customer != null && credentials.getPassword().equals(customer.getCredentials().getPassword())) {
            // to check if the product ID and count are available
            for(int i=0;i < product.size();i++) {
                if (productRepository.getProduct(product.get(i)) != null) {
                    i++;
                    if(!productRepository.checkCount(product.get(i),product.get(i-1))) {
                        return false;
                    }
                }
            }

            SimpleOrder order = new SimpleOrder();
            for(int i=0;i < product.size();i++) {
                order.
            }
        }

        return false;
    }
}
