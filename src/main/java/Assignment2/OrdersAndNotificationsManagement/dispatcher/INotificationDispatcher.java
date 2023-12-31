package Assignment2.OrdersAndNotificationsManagement.dispatcher;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;

public interface INotificationDispatcher {
    boolean dispatch(Notification notification);
}
