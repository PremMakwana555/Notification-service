package com.prem.notification_service.service;

import com.prem.notification_service.configuration.MailgunProperties;
import com.prem.notification_service.dto.UserNotificationRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Setter
public class MailgunSmsService {


    private final MailgunProperties mailgun;
    private final TemplateEngine templateEngine;
    private final RestTemplate restTemplate;

    public MailgunSmsService(MailgunProperties mailgun, TemplateEngine templateEngine, RestTemplateBuilder restTemplateBuilder) {
        this.mailgun = mailgun;
        this.templateEngine = templateEngine;
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendSms(String to, String userName) {

        Context context = new Context();
        context.setVariable("userName", userName);
        String htmlBody = templateEngine.process("sms/UserRegistrationSuccess", context);

        String url = mailgun.getEmailApiBaseUrl() + "/messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("api", mailgun.getApiKey());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("from", mailgun.getFromNumber());
        body.add("to", to); // Must be in E.164 format, e.g., +919xxxxxxxxx
        body.add("text", htmlBody);
        body.add("messaging_service_sid", mailgun.getMessagingServiceId() ); // required for some use cases

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("SMS sent successfully to " + to);
        } else {
            System.err.println("Failed to send SMS: " + response.getBody());
        }
    }

    public void sendUSerOtpVerificationNotification(UserNotificationRequest userNotificationRequest) {
        Context context = new Context();
        context.setVariable("userName", userNotificationRequest.getUserName());
        String htmlBody = templateEngine.process("sms/UserOtpVerification", context);
    }

    public void sendUSerRegistrationSuccessNotification(UserNotificationRequest userNotificationRequest) {
        Context context = new Context();
        context.setVariable("userName", userNotificationRequest.getUserName());
        String htmlBody = templateEngine.process("sms/UserRegistrationSuccess", context);
    }
}

