package com.prem.notification_service.service;

import com.prem.notification_service.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationStrategy implements NotificationStrategy {
    public void send(NotificationRequest request) { /* send SMS */ }
}
