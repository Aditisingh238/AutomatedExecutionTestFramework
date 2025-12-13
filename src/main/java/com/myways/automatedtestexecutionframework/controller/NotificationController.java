package com.myways.automatedtestexecutionframework.controller;
import com.myways.automatedtestexecutionframework.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    ///emailService****************

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody Map<String, String> body) {
        String to = body.getOrDefault("to", null);
        String subject = body.getOrDefault("subject", "Automated Notification");
        String message = body.getOrDefault("message", "");
        if (to == null || to.isBlank()) {
            // use configured recipients
            emailService.sendSimpleMail(subject, message);
        } else {
            // Simple single-send for convenience
            emailService.sendSimpleMail(subject, message); // sends to configured recipients
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "SENT");
        resp.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(resp);
    }
}
