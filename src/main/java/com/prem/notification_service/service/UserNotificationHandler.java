package com.prem.notification_service.service;


import com.prem.notification_service.dto.NotificationChannel;
import com.prem.notification_service.dto.NotificationRequest;
import com.prem.notification_service.dto.UserNotificationRequest;

public class UserNotificationHandler implements NotificationHandler{

    private MailgunApiEmailService mailgunApiEmailService;
    private MailgunSmsService mailgunSmsService;

    public UserNotificationHandler(MailgunApiEmailService mailgunApiEmailService, MailgunSmsService mailgunSmsService) {
        this.mailgunApiEmailService = mailgunApiEmailService;
        this.mailgunSmsService = mailgunSmsService;
    }

    @Override
    public void handleNotification(NotificationRequest notificationRequest) {

        UserNotificationRequest userNotificationRequest = (UserNotificationRequest) notificationRequest;
        switch (userNotificationRequest.getType()) {
            case PASSWORD_RESET -> sendUserPasswordResetNotification(userNotificationRequest);
            case OTP -> sendOtpVerificationNotification(userNotificationRequest);
            case REGISTRATION_SUCCESS -> sendUserRegistrationSuccessNotification(userNotificationRequest);
        }
    }


    private void sendUserRegistrationSuccessNotification(UserNotificationRequest notificationRequest) {

        if(notificationRequest.getChannel() == NotificationChannel.BOTH){
            mailgunSmsService.sendUSerRegistrationSuccessNotification(notificationRequest);
            mailgunApiEmailService.sendUserRegistrationSuccessNotification(notificationRequest);
        }

    }

    private void sendUserPasswordResetNotification(UserNotificationRequest notificationRequest) {
        mailgunApiEmailService.sendUserPasswordResetNotification(notificationRequest);

    }

    private void sendOtpVerificationNotification(UserNotificationRequest notificationRequest) {

        mailgunSmsService.sendUSerOtpVerificationNotification(notificationRequest);
    }
}
