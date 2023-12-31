package Assignment2.OrdersAndNotificationsManagement.controller;

import Assignment2.OrdersAndNotificationsManagement.model.notification.Notification;
import Assignment2.OrdersAndNotificationsManagement.repository.classes.NotificationRepository;
import Assignment2.OrdersAndNotificationsManagement.repository.interfaces.INotificationService;
import Assignment2.OrdersAndNotificationsManagement.service.classes.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping("/queue")
    public List<Notification> getAll() {
        return notificationService.getAll();
    }

    @GetMapping("/stats")
    public Map<String, Map<String, Integer>> getStats() {
        return notificationService.getStats();
    }
}
