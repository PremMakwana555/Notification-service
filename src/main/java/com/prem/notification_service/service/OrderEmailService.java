package com.prem.notification_service.service;

import com.prem.notification_service.dto.OrderNotificationRequest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class OrderEmailService {

    private final BaseEmailService emailService;
    private final TemplateEngine templateEngine;

    public OrderEmailService(BaseEmailService emailService, TemplateEngine templateEngine) {
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public void sendOrderConfirmedEmail(OrderNotificationRequest request) {
        Context context = new Context();
        context.setVariable("orderId", request.orderId());
        context.setVariable("total", request.total());
        context.setVariable("deliveryAddress", request.deliveryAddress());
        String htmlBody = templateEngine.process("OrderConfirmed", context);
        emailService.send(request.recipient(), "Order Confirmed - Order #" + request.orderId(), htmlBody);
    }

    public void sendOrderDeliveredEmail(OrderNotificationRequest request) {
        Context context = new Context();
        context.setVariable("orderId", request.orderId());
        String htmlBody = templateEngine.process("OrderDelivered", context);
        emailService.send(request.recipient(), "Order Delivered - Order #" + request.orderId(), htmlBody);
    }

    public void sendOrderCancelledEmail(OrderNotificationRequest request) {
        Context context = new Context();
        context.setVariable("orderId", request.orderId());
        String htmlBody = templateEngine.process("OrderCancelled", context);
        emailService.send(request.recipient(), "Order Cancelled - Order #" + request.orderId(), htmlBody);
    }
}
//just refactor this and then tes everything!!!!!!