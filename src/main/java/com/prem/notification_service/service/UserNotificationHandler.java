package com.prem.notification_service.service;


import com.prem.notification_service.dto.NotificationChannel;
import com.prem.notification_service.dto.NotificationMetaData;
import com.prem.notification_service.dto.NotificationRequest;
import com.prem.notification_service.dto.UserNotificationRequest;

public class UserNotificationHandler implements NotificationHandler{

    private final UserEmailService userEmailService;
    private final MailgunSmsService mailgunSmsService;

    public UserNotificationHandler(UserEmailService userEmailService, MailgunSmsService mailgunSmsService) {
        this.userEmailService = userEmailService;
        this.mailgunSmsService = mailgunSmsService;
    }

    @Override
    public void handleNotification(NotificationRequest notificationRequest) {

        UserNotificationRequest userNotificationRequest = (UserNotificationRequest) notificationRequest;
        switch (userNotificationRequest.getNotificationType()) {
            case PASSWORD_RESET -> sendUserPasswordResetNotification(userNotificationRequest);
            case OTP -> sendOtpVerificationNotification(userNotificationRequest);
            case REGISTRATION_SUCCESS -> sendUserRegistrationSuccessNotification(userNotificationRequest);
        }
    }


    private void sendUserRegistrationSuccessNotification(UserNotificationRequest notificationRequest) {

        if(notificationRequest.notificationMetaData().channel() == NotificationChannel.BOTH){
            mailgunSmsService.sendUSerRegistrationSuccessNotification(notificationRequest);
            userEmailService.sendUserRegistrationSuccessNotification(notificationRequest);
        }

    }

    private void sendUserPasswordResetNotification(UserNotificationRequest notificationRequest) {
        userEmailService.sendUserPasswordResetNotification(notificationRequest);

    }

    private void sendOtpVerificationNotification(UserNotificationRequest notificationRequest) {

        mailgunSmsService.sendUSerOtpVerificationNotification(notificationRequest);
    }
}
