package Assignment2.OrdersAndNotificationsManagement.model.notification.template;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

import java.util.List;
import java.util.Map;

public class OrderShippedTemplate extends NotificationTemplate {
    public OrderShippedTemplate(String templateId, Language[] languages) {
        super(templateId, languages);
    }

    @Override
    public Notification createNotification(Class<?> channel, String recipient, Map<String, String> arguments, Language language) {
        return null;
    }

    @Override
    public List<Class<?>> getAvailableChannels() {
        return null;
    }

    public Language[] getLanguages() {
        return new Language[]{Language.English};
    }
}
