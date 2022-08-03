package com.hospital.management.notification.application.ports.out;

public interface NotificationUseCase {
    void sendNotification(String message, String mobileNumber);
}
