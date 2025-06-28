package com.prem.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationRequest extends NotificationRequest{

    String userName;
    String userEmail;
    long userContactNumber;
    long otp;
    String passwordResetLink;
}
