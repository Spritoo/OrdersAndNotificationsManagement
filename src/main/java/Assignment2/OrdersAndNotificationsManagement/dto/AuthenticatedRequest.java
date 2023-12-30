package Assignment2.OrdersAndNotificationsManagement.dto;

import Assignment2.OrdersAndNotificationsManagement.model.user.Credentials;

public class AuthenticatedRequest<PayloadType> {
    private Credentials credentials;
    private PayloadType payload;

    public AuthenticatedRequest(Credentials credentials, PayloadType body) {
        this.credentials = credentials;
        this.payload = body;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }
}
