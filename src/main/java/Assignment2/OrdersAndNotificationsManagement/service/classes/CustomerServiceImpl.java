package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.dto.CustomerFormDTO;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.CustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.ICustomerRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.IProductRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.ProductRepository;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository customerRepository = CustomerRepository.getInstance();
    private final IProductRepository productRepository = ProductRepository.getInstance();

    @Override
    public Customer createAccount(Credentials credentials, UserInfo userInfo) {
        return customerRepository.add(credentials, userInfo);
    }

    @Override
    public List<Integer> listFriends(int ID) {
        return customerRepository.GetFriends(ID);
    }

    @Override
    public boolean addFriend(int userId, int friendId) {
        Customer customer = customerRepository.getCustomerById(userId);
        Customer friend = customerRepository.getCustomerById(friendId);

        System.out.println(customer);
        System.out.println(friendId);

        if (customer == null || friend == null) {
            return false;
        }

        customerRepository.addFriend(customer, friend);

        return true;
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

    @Override
    public boolean updateCustomerBalance(Credentials credentials,double newBalance) {
        Customer customer = customerRepository.getCustomerByCredentials(credentials);

        if(customer == null) {
            return false;
        }

        double oldBalance = customer.getUserInfo().getBalance();

        customerRepository.getCustomerByCredentials(credentials).getUserInfo().setBalance(oldBalance + newBalance);

        return true;
    }
}
