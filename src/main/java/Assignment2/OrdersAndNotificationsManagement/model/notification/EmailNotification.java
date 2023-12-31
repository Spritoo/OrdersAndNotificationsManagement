package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class EmailNotification extends Notification {
    private String recipientAddress;
    private String subject;
    private String body;

    public EmailNotification(
            String id,
            String recipientAddress,
            String subject,
            String body,
            Map<String, String> arguments,
            Language language
    ) {
        super(id, arguments, language);

        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
