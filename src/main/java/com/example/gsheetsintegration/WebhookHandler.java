package com.example.gsheetsintegration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookHandler {

    @RequestMapping("/call")
    @ResponseBody
    public String handleIncomingCall(@RequestParam("From") String from) {
        return "Hello from " + from;
    }
}