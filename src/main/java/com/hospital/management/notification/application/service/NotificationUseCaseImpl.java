package com.hospital.management.notification.application.service;

import com.hospital.management.notification.application.ports.out.NotificationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationUseCaseImpl implements NotificationUseCase {

    @Override
    public void sendNotification(String message, String mobileNumber) {
        //todo implement notification system, for now logging message here
        log.info(message);
    }
}
