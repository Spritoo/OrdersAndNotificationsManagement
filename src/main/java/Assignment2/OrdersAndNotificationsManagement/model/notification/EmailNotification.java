package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class EmailNotification extends Notification {
    private String subject;
    private String body;

    public EmailNotification(
            String id,
            String subject,
            String body,
            String recipient,
            Map<String, String> arguments,
            Language language
    ) {
        super(id, recipient, arguments, language);

        this.subject = subject;
        this.body = body;
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
