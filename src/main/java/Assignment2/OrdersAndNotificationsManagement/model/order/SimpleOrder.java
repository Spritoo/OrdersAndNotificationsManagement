package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.model.Product;
import Assignment2.OrdersAndNotificationsManagement.repository.IProductRepository;

import java.util.List;

public class SimpleOrder implements IOrder{
    private List<Product> productList;
    private int OrderID;
    private String simpleOrderOwner;
    private IProductRepository  productRepository;
    SimpleOrder(String costumer){
        simpleOrderOwner = costumer;
    }

    public void addProduct(int productID){
        productRepository.updateCount(productRepository.getProductCount(productID)-1,productID);
        productList.add(productRepository.getProduct(productID));
    }

    public List<Product> getInfo(){

        return productList;
    }

    @Override
    public String getOwner() {
        return simpleOrderOwner;
    }

    @Override
    public int getOrderId(){
        return OrderID;
    }

    @Override
    public void setOrderID(int orderId)
    {
        this.OrderID = orderId;
    }
    @Override
    public void setOwner(String owner) {
        simpleOrderOwner = owner;
    }

    public void removeOrder(Product product) {
        productList.remove(product);
    }
}
