package Assignment2.OrdersAndNotificationsManagement.model.user;

import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.IOrder;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Credentials credentials;
    private UserInfo userInfo;
    private List<String> friends;
    private CompoundOrder order;
    public Customer(Credentials credentials,UserInfo info) {
        this.credentials = credentials;
        this.userInfo = info;
        friends = new ArrayList<>();
        order = new CompoundOrder(userInfo.getEmail());
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


    public IOrder getOrders() {
        return order;
    }

    public void addProductToOrder(int productID){
        order.addProduct(productID,userInfo.getEmail());
    }

    public void addFriend(String friend){
        friends.add(friend);
    }

}
