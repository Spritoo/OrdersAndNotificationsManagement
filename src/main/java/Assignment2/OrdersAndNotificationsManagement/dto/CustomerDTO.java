package Assignment2.OrdersAndNotificationsManagement.dto;

import Assignment2.OrdersAndNotificationsManagement.model.user.UserInfo;

public class CustomerDTO {
    private int id;
    private UserInfo userInfo;

    public CustomerDTO(int id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
