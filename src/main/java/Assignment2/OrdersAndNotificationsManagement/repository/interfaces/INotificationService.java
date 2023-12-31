package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;

import java.util.List;
import java.util.Map;

public interface INotificationService {
    public boolean sendNotification(
            Class<?> templateId,
            Map<String, String> parameters,
            Customer recipient,
            Language language
    );

    public List<Notification> getAll();
}
