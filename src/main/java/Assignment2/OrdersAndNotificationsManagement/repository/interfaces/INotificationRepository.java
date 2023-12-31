package Assignment2.OrdersAndNotificationsManagement.repository.interfaces;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

import java.util.List;

public interface INotificationRepository {
    void add(Notification notification);
    List<Notification> getAll();
    boolean isEmpty();
    List<Notification> flush();
}
