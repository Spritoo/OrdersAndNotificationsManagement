package Assignment2.OrdersAndNotificationsManagement.model.notification.template;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.SMSNotification;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.List;
import java.util.Map;

public class OrderShippedTemplate extends NotificationTemplate {
    @Override
    public Notification createNotification(Class<?> channel, Customer recipient, Map<String, String> arguments, Language language) {
        if (channel == SMSNotification.class) {
            return new SMSNotification(
                    format("Dear {firstName}, your order [{orderId}] has been shipped.", arguments),
                    recipient.getUserInfo().getPhone()
            );
        }

        return null;
    }

    @Override
    public Class<?>[] getAvailableChannels() {
        return new Class[]{SMSNotification.class};
    }

    public Language[] getLanguages() {
        return new Language[]{Language.English};
    }
}
