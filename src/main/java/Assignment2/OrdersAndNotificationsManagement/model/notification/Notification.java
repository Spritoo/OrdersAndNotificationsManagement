package Assignment2.OrdersAndNotificationsManagement.model.notification;

public class Notification {
    private String notificationId;
    private String recipient;
    private NotificationTemplate template;
    private String content;
    private String status; //sent, pending

    public Notification(String notificationId, String recipient, NotificationTemplate template, String content, String status) {
        this.notificationId = notificationId;
        this.recipient = recipient;
        this.template = template;
        this.content = content;
        this.status = status;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public NotificationTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NotificationTemplate template) {
        this.template = template;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", recipient='" + recipient + '\'' +
                ", template='" + template + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
