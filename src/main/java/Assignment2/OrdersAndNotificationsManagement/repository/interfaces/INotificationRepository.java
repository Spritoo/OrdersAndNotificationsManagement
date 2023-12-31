package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;

import java.util.List;

public interface INotificationRepository {
    void add(Notification notification);
    boolean isEmpty();
    List<Notification> flush();
}
