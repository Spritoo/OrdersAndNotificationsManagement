package Assignment2.OrdersAndNotificationsManagement.model;

public class Product {
    String serialNumber;
    String name;
    String Vendor;
    String category;
    float price;
    int count;
    public void printInfo(){
        System.out.println("Product name: " + name + " Price: " + price);
    }
}
