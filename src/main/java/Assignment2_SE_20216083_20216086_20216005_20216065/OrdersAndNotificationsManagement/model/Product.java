package Assignment2_SE_20216083_20216086_20216005_20216065.OrdersAndNotificationsManagement.model;

public class Product {
    String serialNumber;
    String name;
    String category;
    int price;
    public void printInfo(){
        System.out.println("Product name: " + name + "Price: " + price);
    }
}
