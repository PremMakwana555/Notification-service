package com.prem.notification_service.service;

import com.prem.notification_service.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationStrategy implements NotificationStrategy {

    MailgunApiEmailService mailgunApiEmailService;
    public void send(NotificationRequest request) {


    }
}

