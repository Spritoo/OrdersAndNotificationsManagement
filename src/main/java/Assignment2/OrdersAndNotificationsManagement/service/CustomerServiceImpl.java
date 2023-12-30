package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.repository.CustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.repository.ICustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.IProductRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final ICustomerRepository customerRepository = CustomerRepository.getInstance();
    private final IProductRepository productRepository = ProductRepository.getInstance();

    @Override
    public boolean createAccount(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public List<String> listFriends(String email) {
        return customerRepository.GetFriends(email);
    }

    @Override
    public boolean addFriend(Credentials credentials, String friendEmail) {
        Customer customer = customerRepository.getCustomer(credentials.getEmail());
        Customer friend = customerRepository.getCustomer(friendEmail);

        if(customer != null && friend != null && credentials.getPassword().equals(customer.getCredentials().getPassword())) {
            return customerRepository.addfriend(credentials.getEmail(), friendEmail);
        }

        return false;
    }

    @Override
    public UserInfo getCustomerInfo(Credentials credentials) {
        return customerRepository.getCustomerInfo(credentials);
    }
    @Override
    public boolean addProductToOrder(Credentials credentials, int  productID){
        Customer customer = customerRepository.getCustomer(credentials.getEmail());
        if(customer != null && productRepository.checkCount(1,productID)) {
            customer.addProductToOrder(productID);
        }
        return true;
    }
    public boolean addFriendsOrderToMyOrder(Credentials credentials,String friendMail){
        Customer customer = customerRepository.getCustomer(credentials.getEmail());
        if(customer != null && )
    }
}
