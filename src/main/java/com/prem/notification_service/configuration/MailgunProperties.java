package com.prem.notification_service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mailgun")
public class MailgunProperties {
    private String apiKey;
    private String domain;
    private String smsApiBaseUrl;
    private String emailApiBaseUrl;
    private String fromNumber;
    private String fromMail;
    private String messagingServiceId; // optional if using Mailgun SMS
}
