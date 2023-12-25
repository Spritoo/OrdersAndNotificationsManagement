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

    public void setName(String name) {
        Name = name;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getCategory() {
        return Category;
    }

    public String getVendor() {
        return Vendor;
    }

    public String getName() {
        return Name;
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
