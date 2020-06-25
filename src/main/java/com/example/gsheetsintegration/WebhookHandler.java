package com.example.gsheetsintegration;

import com.example.gsheetsintegration.actions.Action;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookHandler {

    @RequestMapping("/call")
    @ResponseBody
    public String handleIncomingCall(@RequestParam("From") String from) {

        if ("Matthew".equals(from)){
            return Action.create("Block", "Sorry Matthew, can't talk now").generateTwiml();

        } else {
            return Action.create("Forward", "123456789").generateTwiml();
        }

    }
}