package Assignment2.OrdersAndNotificationsManagement.service;

import Assignment2.OrdersAndNotificationsManagement.repository.IProductRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.ProductRepository;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private IProductRepository productRepository = ProductRepository.getInstance();

    public ProductServiceImpl() {
        addProducts();
    }

    @Override
    public List<Product> listAllProductsForCustomer() {
        List<Product> products = List.of(productRepository.getProducts());

        return products;
    }

    public void addProducts() {
        productRepository.addProduct(new Product(1101,"shampo","koria","healthCare",12.5,10));
        productRepository.addProduct(new Product(1102,"pizza","china","food",5.25,30));
        productRepository.addProduct(new Product(1103,"Iphone25","apple","games",600,3));
        productRepository.addProduct(new Product(1104, "toothpaste", "USA", "healthCare", 2.99, 50));
        productRepository.addProduct(new Product(1105, "sushi rolls", "Japan", "food", 8.75, 20));
        productRepository.addProduct(new Product(1106, "smartwatch", "South Korea", "electronics", 89.99, 15));
        productRepository.addProduct(new Product(1107, "yoga mat", "India", "fitness", 15.50, 40));
        productRepository.addProduct(new Product(1108, "headphones", "Germany", "electronics", 49.99, 25));
        productRepository.addProduct(new Product(1109, "notebook", "Vietnam", "stationery", 3.75, 100));
        productRepository.addProduct(new Product(1110, "running shoes", "USA", "sports", 59.95, 30));
        productRepository.addProduct(new Product(1111, "frozen berries", "Canada", "food", 6.50, 15));
        productRepository.addProduct(new Product(1112, "gaming laptop", "Taiwan", "electronics", 1200, 5));
        productRepository.addProduct(new Product(1113, "sunscreen", "Australia", "healthCare", 8.99, 20));
    }
}
