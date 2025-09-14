package com.prem.notification_service.dto;


public record NotificationMetaData(NotificationType type,
                                   NotificationChannel channel,
                                   String userContact,
                                   String userName,
                                   String userEmail){

}