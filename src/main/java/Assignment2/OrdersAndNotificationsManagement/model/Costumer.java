package Assignment2.OrdersAndNotificationsManagement.model;

import Assignment2.OrdersAndNotificationsManagement.model.Order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class Costumer {
    private int ID;
    private double balance;
    String username;
    private String password;
    private String phone;
    private List<Costumer> friends;
    private List<IOrder> orders;
    public Costumer() {
        ID = 0;
        username = "";
        password = "";
        phone = "";
        balance = 0.0;
    }
    public Costumer(int ID , String username, String password, double balance , String phone ) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.phone = phone;
        friends = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public List<Costumer> getFriends() {
        return friends;
    }
    public void addFriends(Costumer costumer) {
        friends.add(costumer);
    }

}
