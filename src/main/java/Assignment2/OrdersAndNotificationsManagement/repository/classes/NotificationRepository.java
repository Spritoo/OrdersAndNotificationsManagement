package Assignment2.OrdersAndNotificationsManagement.repository.classes;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository implements INotificationRepository {
    private static NotificationRepository instance;
    public List<Notification> queue = new ArrayList<>();

    public static NotificationRepository getInstance() {
        if (instance == null) {
            instance = new NotificationRepository();
        }

        return instance;
    }

    public NotificationRepository() {
        Notification notification = new Notification();

        add(notification);
    }

    public void add(Notification notification) {
        queue.add(notification);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public List<Notification> flush() {
        List<Notification> result = new ArrayList<>(queue);

        queue.clear();

        return result;
    }
}
