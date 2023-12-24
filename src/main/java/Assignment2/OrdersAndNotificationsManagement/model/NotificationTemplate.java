package Assignment2.OrdersAndNotificationsManagement.model;

import java.util.List;
import java.util.Map;

public class NotificationTemplate {
    private String templateId;
    private String templateType; // order placement or shipment
    private String subject;
    private String content;
    private List<String> placeholders;

    public NotificationTemplate(String templateId, String templateType, String subject, String content, List<String> placeholders) {
        this.templateId = templateId;
        this.templateType = templateType;
        this.subject = subject;
        this.content = content;
        this.placeholders = placeholders;
    }

    public String generateNotificationContent(Map<String, String> placeholderValues, String language) {
        String notificationContent = this.content;
        for (Map.Entry<String, String> entry : placeholderValues.entrySet()) {
            String placeholder = entry.getKey();
            String value = entry.getValue();
            notificationContent = notificationContent.replace("{" + placeholder + "}", value);
        }
        return notificationContent;
    }
}
