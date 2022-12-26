package com.mobicast.telegram4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobicast.telegram4j.domain.GitlabRequestBody;
import com.mobicast.telegram4j.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    @Autowired
    public TelegramService telegramService;
    @PostMapping
    public ResponseEntity<String> print(@RequestBody String requestBody){
        System.out.println("### Webhook ### " + requestBody);
        telegramService.notify(requestBody);
        return new ResponseEntity<String>("OK !!!", HttpStatus.OK);
    }
}
