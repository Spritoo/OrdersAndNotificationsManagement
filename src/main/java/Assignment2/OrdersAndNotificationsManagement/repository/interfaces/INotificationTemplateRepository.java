package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.notification.template.NotificationTemplate;

public interface INotificationTemplateRepository {
    NotificationTemplate get(Class<?> type);
    void add(NotificationTemplate template);
}
