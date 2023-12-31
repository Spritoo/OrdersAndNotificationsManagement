package Assignment2.OrdersAndNotificationsManagement.model.notification;

import Assignment2.OrdersAndNotificationsManagement.model.Language;

import java.util.Map;

public class Notification {
    private String id;
    private String type;
    private String recipient;
    private String templateId;
    private Map<String, String> arguments;
    private Language language;

    public Notification(
            String id,
            String type,
            String recipient,
            String templateId,
            Map<String, String> arguments,
            Language language
    ) {
        this.id = id;
        this.type = type;
        this.recipient = recipient;
        this.templateId = templateId;
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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String template) {
        this.templateId = template;
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

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", recipient='" + recipient + '\'' +
                ", templateId='" + templateId + '\'' +
                ", arguments=" + arguments +
                ", language=" + language +
                '}';
    }
}
