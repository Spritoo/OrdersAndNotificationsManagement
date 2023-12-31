package Assignment2.OrdersAndNotificationsManagement.repository.classess;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationRepository;

import java.util.List;

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
