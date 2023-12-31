package Assignment2.OrdersAndNotificationsManagement.model.notification.template;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.List;
import java.util.Map;

// OrderNotificationTemplate message w fe subject mo3ayana
// 1. class esmo OrderNotificationTemplate
// 2. instance of NotificationTemplate called orderNotificationTemplate and store it somewhere and reuse it when sending that notification type

public abstract class NotificationTemplate {
    protected String format(String message, Map<String, String> arguments) {
        for (Map.Entry<String, String> entry: arguments.entrySet()) {
            String placeholderName = entry.getKey();
            String value = entry.getValue();

            message = message.replace("{" + placeholderName + "}", value);
        }

        return message;
    }

    public abstract Class<?>[]  getAvailableChannels();
    public abstract Notification createNotification(
            Class<?> channel,
            Customer recipient,
            Map<String, String> arguments,
            Language language
    );

    public Class<?> getIdentifier() {
        return this.getClass();
    }

    public abstract Language[] getLanguages();
}
