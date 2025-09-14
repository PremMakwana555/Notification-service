package com.prem.notification_service.service;

public interface BaseEmailService {

    void send(String to, String subject, String htmlBody);
}
