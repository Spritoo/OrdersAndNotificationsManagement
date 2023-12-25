package Assignment2.OrdersAndNotificationsManagement.model.notification;

public class Notification {
    private String notificationId;
    private String recipient;
    private String templateType;
    private String content;
    private String status; //sent, pending

    public Notification(String notificationId, String recipient, String templateType, String content, String status) {
        this.notificationId = notificationId;
        this.recipient = recipient;
        this.templateType = templateType;
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

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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
                ", templateType='" + templateType + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
