package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepository implements INotificationRepository {
    private static NotificationRepository instance;
    public List<Notification> queue;

    public static NotificationRepository getInstance() {
        if (instance == null) {
            instance = new NotificationRepository();
        }

        return instance;
    }

    public void add(Notification notification) {
        queue.add(notification);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Notification popFront() {
        return queue.removeFirst();
    }
}
