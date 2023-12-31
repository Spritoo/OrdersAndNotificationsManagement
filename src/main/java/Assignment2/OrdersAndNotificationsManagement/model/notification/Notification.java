package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public abstract class Notification {
    private String id;
    private String recipient;

    public Notification(String recipient) {
        this.recipient = recipient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Class<?> getType() {
        return this.getClass();
    }
}
