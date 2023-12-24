package Assignment2.OrdersAndNotificationsManagement.model;

public class Product {
    String SerialNumber;
    String Name;
    String Vendor;
    String Category;
    float Price;
    int Count;
    public Product(String serial, String name, String vendor, String category, float price, int count) {
        SerialNumber = serial;
        Name = name;
        Vendor = vendor;
        Category = category;
        Price = price;
        Count = count;
    }
    public void printInfo(){ // this shouldn't be here
        System.out.println("Product name: " + Name + " Price: " + Price);
    }
    public void setCount(int count) {
        Count = count;
    }
    public int getCount() {
        return Count;
    }
}
