package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

import java.util.List;
import java.util.Map;

public interface INotificationService {
    public boolean sendNotification(
            Class<?> templateId,
            Map<String, String> parameters,
            String recipient,
            Language language
    );

    public List<Notification> getAll();
}
