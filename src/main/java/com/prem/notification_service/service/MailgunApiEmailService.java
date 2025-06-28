package com.prem.notification_service.service;

import com.prem.notification_service.configuration.MailgunProperties;
import com.prem.notification_service.dto.NotificationRequest;
import com.prem.notification_service.dto.UserNotificationRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailgunApiEmailService {

    private final MailgunProperties mailgun;
    private final TemplateEngine templateEngine;
    private final RestTemplate restTemplate;

    public MailgunApiEmailService(
            MailgunProperties mailgun,
            TemplateEngine templateEngine,
            RestTemplateBuilder restTemplateBuilder
    ) {
        this.mailgun = mailgun;
        this.templateEngine = templateEngine;
        this.restTemplate = restTemplateBuilder.build(); // allows injection of interceptors, timeouts
    }

    public void sendUserRegistrationSuccessNotification(UserNotificationRequest userNotificationRequest) {

        Context context = new Context();
        context.setVariable("userName", userNotificationRequest.getUserName());
        String htmlBody = templateEngine.process("UserRegistrationSuccess", context);
        send(userNotificationRequest.getUserEmail(),  htmlBody);
    }

    public void sendUserPasswordResetNotification(UserNotificationRequest userNotificationRequest) {
        Context context = new Context();
        context.setVariable("userName", userNotificationRequest.getUserName());
        context.setVariable("resetLink", userNotificationRequest.getPasswordResetLink());
        String htmlBody = templateEngine.process("UserPasswordReset", context);
        send(userNotificationRequest.getUserEmail(),  htmlBody);
    }



    public void send(String to, String htmlBody) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("api", mailgun.getApiKey());

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("from", mailgun.getFromMail());
        form.add("to", to);
        form.add("subject", "Registration successful with ChicSplash!");
        form.add("text", htmlBody);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        String mailgunUrl = "https://api.mailgun.net/v3/" + mailgun.getDomain() + "/messages";

        restTemplate.postForEntity(mailgunUrl, request, String.class);
    }

}
