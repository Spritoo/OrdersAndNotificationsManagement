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

import java.util.List;

@Service
public class NotificationService {
    // notification dispatch rate in milliseconds
    private static final int NOTIFICATION_TIMER_RATE = 5000; // 5s
    private INotificationRepository notificationRepository = NotificationRepository.getInstance();

    public void add(Notification notification) {
        notificationRepository.add(notification);
    }

    private INotificationDispatcher createNotificationDispatcher(String type) {
        return switch (type) {
            case "email" -> new EmailNotificationDispatcher();
            case "sms" -> new SMSNotificationDispatcher();
            default -> null;
        };
    }

    @Scheduled(fixedRate = NOTIFICATION_TIMER_RATE)
    public void dispatch() {
        System.out.println("Hello world!");

        List<Notification> notifications = notificationRepository.flush();

        for (Notification notification: notifications) {
            String templateId = notification.getTemplateId();
            // NotificationTemplate template = NotificationTemplateRepository.getInstance().getTemplate(templateId);

            INotificationDispatcher dispatcher = createNotificationDispatcher(notification.getType());
        }
    }
}
