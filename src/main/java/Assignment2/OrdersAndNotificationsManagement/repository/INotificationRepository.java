package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;

public interface INotificationRepository {
    void add(Notification notification);
    boolean isEmpty();
    Notification popFront();
}
