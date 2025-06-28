package com.prem.notification_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    private NotificationType type;          // OTP, REGISTRATION_SUCCESS, PASSWORD_RESET
    private NotificationChannel channel;            // SMS, EMAIL
    private String recipient;                 // email or phone
}
