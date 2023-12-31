package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class SMSNotification extends Notification {
    private String recipientNumber;
    private String message;

    public SMSNotification(
            String id,
            String recipientNumber,
            String message,
            Map<String, String> arguments,
            Language language
    ) {
        super(id, arguments, language);

        this.recipientNumber = recipientNumber;
        this.message = message;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
