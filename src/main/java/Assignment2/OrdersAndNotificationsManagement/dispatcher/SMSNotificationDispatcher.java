package Assignment2.OrdersAndNotificationsManagement.dispatcher;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.model.notification.SMSNotification;

public class SMSNotificationDispatcher implements INotificationDispatcher {
    @Override
    public boolean dispatch(Notification notification) {
        return dispatchInternal((SMSNotification) notification);
    }

    private boolean dispatchInternal(SMSNotification notification) {
        System.out.println("SMS notification dispatched");

        return true;
    }

    @Override
    public Class<?> getSupportedNotificationType() {
        return SMSNotification.class;
    }
}
