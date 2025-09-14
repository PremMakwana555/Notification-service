package com.prem.notification_service.service;

import com.prem.notification_service.dto.NotificationMetaData;
import com.prem.notification_service.dto.NotificationRequest;

public interface NotificationHandler {

    void handleNotification(NotificationRequest notificationRequest);
}
