package Assignment2.OrdersAndNotificationsManagement.dispatcher;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

public class EmailNotificationDispatcher implements INotificationDispatcher {
    @Override
    public boolean dispatch(Notification notification) {
        System.out.println("Email notification dispatched");

        return true;
    }
}
