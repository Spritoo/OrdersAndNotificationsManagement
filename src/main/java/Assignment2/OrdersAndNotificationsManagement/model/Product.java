package Assignment2.OrdersAndNotificationsManagement.model;

public class Product {
    int SerialNumber;
    String Name;
    String Vendor;
    String Category;
    double Price;
    int Count;
    public Product(int serial, String name, String vendor, String category, double price, int count) {
        SerialNumber = serial;
        Name = name;
        Vendor = vendor;
        Category = category;
        Price = price;
        Count = count;
    }
    public void setCount(int count) {
        Count = count;
    }
    public int getCount() {
        return Count;
    }
    public int getSerialNumber() {
        return SerialNumber;
    }
}
