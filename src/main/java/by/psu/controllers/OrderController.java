package by.psu.controllers;

import by.psu.email.EmailService;
import by.psu.email.Mail;
import by.psu.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Value("${spring.mail.username}")
    private String email;

    private final EmailService emailService;


    @Autowired
    public OrderController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping()
    public ResponseEntity sendOrder(@RequestBody Order order) {
        Mail mail = new Mail();

        mail.setTo(order.getEmail());
        mail.setFrom(email);
/*
        mail.setSubject("Заказ на Sumo.by");

        mail.setContent(emailService.createOrderEmail(order));
        emailService.sendHtmlMessage(mail);*/
        emailService.sendSimpleMessage(mail);

        return ResponseEntity.ok().build();
    }
}
