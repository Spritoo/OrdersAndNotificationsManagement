package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public abstract class Notification {
    private String id;
    private Map<String, String> arguments;
    private Language language;

    public Notification(
            String id,
            Map<String, String> arguments,
            Language language
    ) {
        this.id = id;
        this.arguments = arguments;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Class<?> getType() {
        return this.getClass();
    }
}
