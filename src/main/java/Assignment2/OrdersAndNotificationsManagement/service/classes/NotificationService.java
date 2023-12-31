package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.dispatcher.EmailNotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.dispatcher.INotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.dispatcher.SMSNotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.NotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    // notification dispatch rate in milliseconds
    private static final int NOTIFICATION_TIMER_RATE = 5000; // 5s

    private INotificationRepository notificationRepository = NotificationRepository.getInstance();
    private Map<Class<?>, INotificationDispatcher> dispatchers = new HashMap<>();

    public NotificationService() {
        dispatchers = Map.of(
            EmailNotificationDispatcher.class, new EmailNotificationDispatcher(),
            SMSNotificationDispatcher.class, new SMSNotificationDispatcher()
        );
    }

    public void add(Notification notification) {
        notificationRepository.add(notification);
    }

    @Scheduled(fixedRate = NOTIFICATION_TIMER_RATE)
    public void dispatch() {
        List<Notification> notifications = notificationRepository.flush();
        INotificationDispatcher dispatcher;

        for (Notification notification: notifications) {
            Class<?> notificationType = notification.getType();
            dispatcher = this.dispatchers.get(notificationType);

            if (dispatcher == null) {
                System.out.println("No dispatcher found for notification type " + notificationType.getName());

                continue;
            }

            dispatcher.dispatch(notification);
        }
    }
}
