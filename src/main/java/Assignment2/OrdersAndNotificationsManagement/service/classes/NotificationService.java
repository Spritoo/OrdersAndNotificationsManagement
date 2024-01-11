package Assignment2.OrdersAndNotificationsManagement.service.classes;

import Assignment2.OrdersAndNotificationsManagement.dispatcher.EmailNotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.dispatcher.INotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.dispatcher.SMSNotificationDispatcher;
import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.EmailNotification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.SMSNotification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.template.NotificationTemplate;
import Assignment2.OrdersAndNotificationsManagement.model.user.Customer;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.NotificationTemplateRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.NotificationRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationTemplateRepository;
import Assignment2.OrdersAndNotificationsManagement.service.interfaces.INotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService implements INotificationService {
    // notification dispatch rate in milliseconds
    private static final int NOTIFICATION_TIMER_RATE = 5000; // 5s

    private INotificationTemplateRepository notificationTemplateRepository = NotificationTemplateRepository.getInstance();
    private INotificationRepository notificationRepository = NotificationRepository.getInstance();
    private Map<Class<?>, INotificationDispatcher> dispatchers = new HashMap<>();
    private Map<String, Map<String, Integer>> recipientCounters = new HashMap<>();

    public NotificationService() {
        dispatchers = Map.of(
            EmailNotification.class, new EmailNotificationDispatcher(),
            SMSNotification.class, new SMSNotificationDispatcher()
        );

        for (Class<?> channel: getAvailableChannels()) {
            recipientCounters.put(channel.getName(), new HashMap<>());
        }
    }

    private List<Class<?>> getAvailableChannels() {
        return new ArrayList<>(dispatchers.keySet());
    }

    private boolean isChannelSupported(Class<?> channel) {
        return getAvailableChannels().contains(channel);
    }

    public boolean sendNotification(
            Class<?> templateId,
            Map<String, String> parameters,
            Customer recipient,
            Language language
    ) {
        NotificationTemplate template = notificationTemplateRepository.get(templateId);

        if (template == null) {
            System.out.println("No notification template found for id " + templateId);

            return false;
        }

        Class<?>[] availableChannels = template.getAvailableChannels();
        int count = 0;

        for (Class<?> channel: availableChannels) {
            if (!isChannelSupported(channel)) {
                System.out.println("No dispatcher found for notification channel " + channel.getName());

                continue;
            }

            Notification notification = template.createNotification(channel, recipient, parameters, language);

            add(notification);
            count++;
        }

        return count > 0;
    }

    public List<Notification> getAll() {
        return notificationRepository.getAll();
    }

    public Map<String, Map<String, Integer>> getStats() {
        return recipientCounters;
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

            recipientCounters.get(notificationType.getName()).merge(notification.getRecipient(), 1, Integer::sum);

            dispatcher.dispatch(notification);
        }
    }
}
