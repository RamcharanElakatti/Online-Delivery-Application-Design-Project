package com.OnlineDeliveryApplication.Notifications;

import com.OnlineDeliveryApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.OnlineDeliveryApplication.ordermanagement.Order;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmation(User user, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Your order with ID " + order.getId() + " has been placed successfully.");
        mailSender.send(message);
    }
}