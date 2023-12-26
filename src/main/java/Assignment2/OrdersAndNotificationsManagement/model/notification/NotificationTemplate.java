package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.List;
import java.util.Map;

// OrderNotificationTemplate message w fe subject mo3ayana
// 1. class esmo OrderNotificationTemplate
// 2. instance of NotificationTemplate called orderNotificationTemplate and store it somewhere and reuse it when sending that notification type

public class NotificationTemplate {
    private String templateId;
    private String subject;
    private String content;
    private Language[] languages;

    public NotificationTemplate(String templateId, String subject, String content, Language[] languages) {
        this.templateId = templateId;
        this.subject = subject;
        this.content = content;
        this.languages = languages;
    }

    // String.formatf() is better, but we implement a custom formatting function to meet the exact requirements
    private String format(String message, Map<String, String> arguments, Language language) {
        for (Map.Entry<String, String> entry: arguments.entrySet()) {
            String placeholderName = entry.getKey();
            String value = entry.getValue();

            message = message.replace("{" + placeholderName + "}", value);
        }

        return message;
    }

    public String buildContent(Map<String, String> arguments, Language language) {
        return format(content, arguments, language);
    }

    public String buildSubject(Map<String, String> arguments, Language language) {
        return format(subject, arguments, language);
    }

    public String getTemplateId() {
        return this.templateId;
    }
}
