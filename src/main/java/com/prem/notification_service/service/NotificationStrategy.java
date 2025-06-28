package com.prem.notification_service.service;

import com.prem.notification_service.dto.NotificationRequest;

public interface NotificationStrategy {

    void send(NotificationRequest request);
}
