package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class SMSNotification extends Notification {
    private String message;

    public SMSNotification(
            String id,
            String message,
            String recipient,
            Map<String, String> arguments,
            Language language
    ) {
        super(id, recipient, arguments, language);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
