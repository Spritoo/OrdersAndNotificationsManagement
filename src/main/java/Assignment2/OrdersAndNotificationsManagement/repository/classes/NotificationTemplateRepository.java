package Assignment2.OrdersAndNotificationsManagement.repository.classes;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.template.NotificationTemplate;
import Assignment2.OrdersAndNotificationsManagement.model.notification.template.OrderPlacedTemplate;
import Assignment2.OrdersAndNotificationsManagement.model.notification.template.OrderShippedTemplate;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationTemplateRepository;

import java.util.HashMap;
import java.util.Map;

public class NotificationTemplateRepository implements INotificationTemplateRepository {
    private static NotificationTemplateRepository instance;

    private final Map<Class<?>, NotificationTemplate> templates = new HashMap<>();

    public static NotificationTemplateRepository getInstance() {
        if (instance == null) {
            instance = new NotificationTemplateRepository();
        }

        return instance;
    }

    private NotificationTemplateRepository() {
        populate();
    }

    private void populate() {
        add(new OrderPlacedTemplate());
        add(new OrderShippedTemplate());
    }

    @Override
    public void add(NotificationTemplate template) {
        templates.put(template.getIdentifier(), template);
    }

    @Override
    public NotificationTemplate get(Class<?> identifier) {
        return templates.get(identifier);
    }
}
