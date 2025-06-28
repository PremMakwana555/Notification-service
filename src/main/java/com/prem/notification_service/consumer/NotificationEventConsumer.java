package com.prem.notification_service.consumer;

import com.prem.notification_service.dto.NotificationRequest;
import com.prem.notification_service.service.UserNotificationHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventConsumer {

    UserNotificationHandler userNotificationHandler;

    @KafkaListener(topics = "user_event")
    public void consumeUserEvent(NotificationRequest NotificationEvent) {

        userNotificationHandler.handleNotification(NotificationEvent);

    }


    @KafkaListener(topics = "order_event")
    public void consumeOrderEvent(OrderEvent orderEvent) {


    }
}
