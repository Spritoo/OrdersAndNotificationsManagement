package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class SMSNotification extends Notification {
    private String message;

    public SMSNotification(
            String message,
            String recipient
    ) {
        super(recipient);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
