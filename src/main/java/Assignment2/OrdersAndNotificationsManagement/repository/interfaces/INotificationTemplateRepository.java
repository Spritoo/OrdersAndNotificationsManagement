package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;

public interface INotificationTemplateRepository {
    NotificationTemplate getTemplate(String templateId);
}