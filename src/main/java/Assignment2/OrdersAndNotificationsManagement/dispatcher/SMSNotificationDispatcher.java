package Assignment2.OrdersAndNotificationsManagement.dispatcher;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

public class SMSNotificationDispatcher implements INotificationDispatcher {
    public boolean dispatch(Notification notification) {
        System.out.println("SMS notification dispatched");

        return true;
    }
}
