package Assignment2.OrdersAndNotificationsManagement.model.user;

import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Credentials credentials;
    private UserInfo userInfo;
    private List<String> friends;
    private List<IOrder> orders;
    public Customer(Credentials credentials,UserInfo info) {
        this.credentials = credentials;
        this.userInfo = info;
        friends = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<String> getFriends() {
        return friends;
    }


    public List<IOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<IOrder> orders) {
        this.orders = orders;
    }

    public void addFriend(String friend){
        friends.add(friend);
    }

}
