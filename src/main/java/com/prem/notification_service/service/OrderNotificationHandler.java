package com.prem.notification_service.service;

import com.prem.notification_service.dto.NotificationRequest;
import com.prem.notification_service.dto.OrderNotificationRequest;

public class OrderNotificationHandler implements NotificationHandler{

    private final OrderEmailService orderEmailService;

    public OrderNotificationHandler(OrderEmailService orderEmailService){
        this.orderEmailService = orderEmailService;
    }

    @Override
    public void handleNotification(NotificationRequest notificationRequest) {
        OrderNotificationRequest orderNotificationRequest = (OrderNotificationRequest) notificationRequest;

        switch (orderNotificationRequest.getNotificationType()){
            case ORDER_CANCELLED -> orderEmailService.sendOrderCancelledEmail(orderNotificationRequest);
            case ORDER_CONFIRMED -> orderEmailService.sendOrderConfirmedEmail(orderNotificationRequest);
            case ORDER_DELIVERED -> orderEmailService.sendOrderDeliveredEmail(orderNotificationRequest);
        }
    }
}
