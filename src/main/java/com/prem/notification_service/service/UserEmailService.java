package com.prem.notification_service.service;

import com.prem.notification_service.dto.UserNotificationRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class UserEmailService {

    private final BaseEmailService emailService;
    private final TemplateEngine templateEngine;

    public UserEmailService(BaseEmailService emailService, TemplateEngine templateEngine) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public void sendUserRegistrationSuccessNotification(UserNotificationRequest request) {
        Context context = new Context();
        context.setVariable("userName", request.notificationMetaData().userName());
        String htmlBody = templateEngine.process("UserRegistrationSuccess", context);
        emailService.send(request.notificationMetaData().userEmail(), "Registration successful with ChicSplash!", htmlBody);
    }

    public void sendUserPasswordResetNotification(UserNotificationRequest request) {
        Context context = new Context();
        context.setVariable("userName", request.notificationMetaData().userName());
        context.setVariable("resetLink", request.passwordResetLink());
        String htmlBody = templateEngine.process("UserPasswordReset", context);
        emailService.send(request.notificationMetaData().userEmail(), "Password Reset Request", htmlBody);
    }
}
