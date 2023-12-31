package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public abstract class Notification {
    private String id;
    private String recipient;
    private Map<String, String> arguments;
    private Language language;

    public Notification(
            String recipient,
            Map<String, String> arguments,
            Language language
    ) {
        this.recipient = recipient;
        this.arguments = arguments;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
