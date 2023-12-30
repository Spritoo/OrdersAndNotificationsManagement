package Assignment2.OrdersAndNotificationsManagement.model.user;

import Assignment2.OrdersAndNotificationsManagement.model.order.CompoundOrder;
import Assignment2.OrdersAndNotificationsManagement.model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    int id;
    private Credentials credentials;
    private UserInfo userInfo;
    private List<Integer> friends;


    // todo: convert friends to List<Customer>

    public Customer(int id, Credentials credentials, UserInfo info) {
        this.credentials = credentials;
        this.userInfo = info;
        this.id = id;

        friends = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getFriends() {
        return friends;
    }

    public void addFriend(Integer friend){
        friends.add(friend);
    }
}
