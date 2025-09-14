package com.prem.notification_service.service;

import com.prem.notification_service.configuration.MailgunProperties;
import com.prem.notification_service.dto.UserNotificationRequest;
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
public class MailgunApiEmailService implements BaseEmailService{

    private final MailgunProperties mailgun;
    private final RestTemplate restTemplate;

    public MailgunApiEmailService(
            MailgunProperties mailgun,
            TemplateEngine templateEngine,
            RestTemplateBuilder restTemplateBuilder
    ) {
        this.mailgun = mailgun;
        this.restTemplate = restTemplateBuilder.build(); // allows injection of interceptors, timeouts
    }



    public void send(String to, String subject, String htmlBody) {

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
