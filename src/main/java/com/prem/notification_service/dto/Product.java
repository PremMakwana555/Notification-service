package com.prem.notification_service.dto;

public record Product (String name,
                       String description,
                       Long quantity,
                       Double price,
                       String productId) {
}
