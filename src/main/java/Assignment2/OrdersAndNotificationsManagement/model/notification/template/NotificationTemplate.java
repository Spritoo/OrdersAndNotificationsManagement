package Assignment2.OrdersAndNotificationsManagement.model.notification.template;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

import java.util.List;
import java.util.Map;

// OrderNotificationTemplate message w fe subject mo3ayana
// 1. class esmo OrderNotificationTemplate
// 2. instance of NotificationTemplate called orderNotificationTemplate and store it somewhere and reuse it when sending that notification type

public abstract class NotificationTemplate {
    private String templateId;

    public NotificationTemplate(String templateId) {
        this.templateId = templateId;
    }

    protected String format(String message, Map<String, String> arguments, Language language) {
        for (Map.Entry<String, String> entry: arguments.entrySet()) {
            String placeholderName = entry.getKey();
            String value = entry.getValue();

            message = message.replace("{" + placeholderName + "}", value);
        }

        return message;
    }

    public abstract List<Class<?>> getAvailableChannels();
    public abstract Notification createNotification(
            Class<?> channel,
            String recipient,
            Map<String, String> arguments,
            Language language
    );

    public String getTemplateId() {
        return this.templateId;
    }

    public abstract Language[] getLanguages();
}
