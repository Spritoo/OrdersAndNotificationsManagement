package Assignment2.OrdersAndNotificationsManagement.repository;

import Assignment2.OrdersAndNotificationsManagement.model.Language;
import Assignment2.OrdersAndNotificationsManagement.model.notification.NotificationTemplate;

import java.util.HashMap;
import java.util.Map;

public class NotificationTemplateRepository implements INotificationTemplateRepository {
    private static NotificationTemplateRepository instance;

    private final Map<String, NotificationTemplate> templates = new HashMap<>();

    public static NotificationTemplateRepository getInstance() {
        if (instance == null) {
            instance = new NotificationTemplateRepository();
        }

        return instance;
    }

    private NotificationTemplateRepository() {
        populate();
    }

    private void addTemplate(NotificationTemplate template) {
        templates.put(template.getTemplateId(), template);
    }

    private void populate() {
        addTemplate(new NotificationTemplate(
                "ORDER_PLACED",
                "Your order has been placed",
                "Dear {firstName}, your order [{orderId}] is confirmed. Thanks for using our store :)",
                new Language[]{Language.English, Language.Arabic}
        ));

        addTemplate(new NotificationTemplate(
                "ORDER_SHIPPED",
                "Your order has been shipped",
                "Dear {firstName}, your order [{}] has been shipped.",
                new Language[]{Language.English, Language.Arabic}
        ));
    }

    @Override
    public NotificationTemplate getTemplate(String id) {
        return templates.get(id);
    }
}
