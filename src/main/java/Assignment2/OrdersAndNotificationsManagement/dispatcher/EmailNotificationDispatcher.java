package Assignment2.OrdersAndNotificationsManagement.dispatcher;

import Assignment2.OrdersAndNotificationsManagement.model.notification.EmailNotification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

public class EmailNotificationDispatcher implements INotificationDispatcher {
    @Override
    public boolean dispatch(Notification notification) {
        return dispatchInternal((EmailNotification) notification);
    }

    private boolean dispatchInternal(EmailNotification notification) {
        System.out.println("SMS notification dispatched");

        return true;
    }

    @Override
    public Class<?> getSupportedNotificationType() {
        return EmailNotification.class;
    }
}
