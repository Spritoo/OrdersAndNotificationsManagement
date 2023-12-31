package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.repository.classess.CustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.ICustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.IProductRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classess.ProductRepository;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository customerRepository = CustomerRepository.getInstance();
    private final IProductRepository productRepository = ProductRepository.getInstance();

    @Override
    public boolean createAccount(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public List<Integer> listFriends(int ID) {
        return customerRepository.GetFriends(ID);
    }

    @Override
    public boolean addFriend(Credentials credentials, int friendID) {
        Customer customer = customerRepository.getCustomerByCredentials(credentials);
        Customer friend = customerRepository.getCustomerById(friendID);

        Credentials customerCredentialsCustomer = customer.getCredentials();

        boolean isRightEmailCustomer = customerCredentialsCustomer.getEmail().equals(credentials.getEmail());
        boolean isRightPasswordCustomer = customerCredentialsCustomer.getPassword().equals(credentials.getPassword());

        if (isRightEmailCustomer && isRightPasswordCustomer) {

            if (customer != null && friend != null) {
                return customerRepository.addfriend(credentials, friendID);
            }
        }

        return false;
    }

    @Override
    public UserInfo getCustomerInfo(Credentials credentials) {
        return customerRepository.getCustomerInfo(credentials);
    }

    @Override
    public Customer authenticate(Credentials credentials) {
        return customerRepository.authenticate(credentials);
    }
}
