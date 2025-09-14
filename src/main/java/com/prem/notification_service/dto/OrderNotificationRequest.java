package com.prem.notification_service.dto;


import java.time.LocalDateTime;
import java.util.List;

public record OrderNotificationRequest(NotificationMetaData notificationMetaData,
                                       NotificationType type,
                                       NotificationChannel channel,
                                       String recipient,
                                       String orderId,
                                       List<Product> productList,
                                       LocalDateTime expectedDeliveryTime,
                                       Long total,
                                       String deliveryAddress
                                       //string invoiceFile
                                       ) implements NotificationRequest {

    public OrderNotificationType getNotificationType(){
        return (OrderNotificationType) notificationMetaData.type();
    }
}
