package com.prem.notification_service.dto;

public record UserNotificationRequest(NotificationMetaData notificationMetaData,
        String userName,
        String userEmail,
        long userContactNumber,
        long otp,
        String passwordResetLink
) implements NotificationRequest {

    public UserNotificationType getNotificationType(){
        return (UserNotificationType) notificationMetaData.type();
    }
}