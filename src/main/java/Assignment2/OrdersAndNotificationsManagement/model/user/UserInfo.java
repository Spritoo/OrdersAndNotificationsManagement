package Assignment2.OrdersAndNotificationsManagement.model.user;

import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.List;

public class UserInfo {
    String email;
    private double balance;
    String username;
    private String phone;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
