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
import java.util.Map;

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

    @Override
    public boolean correctBalance(Credentials credentials, int productID) {
        if(customerRepository.checkBalance(credentials, productRepository.getProduct(productID).getPrice())){
            customerRepository.deductBalance(credentials, productRepository.getProduct(productID).getPrice());

            return true;
        }

        return false;
    }

    @Override
    public void returnBalanceAfterCancellation(Map<Integer, List<Integer>> customerAndProducts) {
        for (Map.Entry<Integer, List<Integer>> entry : customerAndProducts.entrySet()) {
            int customerID = entry.getKey();
            List<Integer> productIDs = entry.getValue();

            Customer customer = customerRepository.getCustomerById(customerID);

            for (int productID : productIDs) {
                double customerBalance = customerRepository.getCustomerById(customerID).getUserInfo().getBalance();
                double productPrice = productRepository.getProduct(productID).getPrice();

                customerRepository.updateBalance(customer, customerBalance + productPrice);
            }
        }
    }

    @Override
    public boolean isFriends(int id, int friend) {
        return customerRepository.isFriends(id, friend);
    }

    @Override
    public boolean decductShipmentFees(Map<Integer, List<Integer>> customerAndProducts) {
        int counter = 0;
        double shippingFees = 50;

        for (Map.Entry<Integer, List<Integer>> entry : customerAndProducts.entrySet()) {
            int customerID = entry.getKey();
            counter++;
        }

        for (Map.Entry<Integer, List<Integer>> entry : customerAndProducts.entrySet()) {
            int customerID = entry.getKey();

            if(customerRepository.getCustomerById(customerID).getUserInfo().getBalance() < shippingFees/counter)
                return false;


            double customerBalance = customerRepository.getCustomerById(customerID).getUserInfo().getBalance();
            customerRepository.getCustomerById(customerID).getUserInfo().setBalance(customerBalance - (shippingFees/counter));
        }

        return true;
    }

    @Override
    public void returnShipmentFees(Map<Integer, List<Integer>> customerAndProducts) {
        int counter = 0;
        double shippingFees = 50;

        for (Map.Entry<Integer, List<Integer>> entry : customerAndProducts.entrySet()) {
            int customerID = entry.getKey();
            counter++;
        }

        for (Map.Entry<Integer, List<Integer>> entry : customerAndProducts.entrySet()) {
            int customerID = entry.getKey();
            double customerBalance = customerRepository.getCustomerById(customerID).getUserInfo().getBalance();
            customerRepository.getCustomerById(customerID).getUserInfo().setBalance(customerBalance + (shippingFees*counter));
        }
    }
}
