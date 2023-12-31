package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classess.NotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    // notification dispatch rate in milliseconds
    private static final int NOTIFICATION_TIMER_RATE = 5000; // 5s
    private INotificationRepository notificationRepository = NotificationRepository.getInstance();

    public void add(Notification notification) {
        notificationRepository.add(notification);
    }

    @Scheduled(fixedRate = NOTIFICATION_TIMER_RATE)
    public void dispatch() {
        Notification notification;

        while (!notificationRepository.isEmpty()) {
            notification = notificationRepository.popFront();

            // dispatch the notification somehow
        }
    }
}