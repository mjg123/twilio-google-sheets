package com.example.gsheetsintegration;

import com.example.gsheetsintegration.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookHandler {

    @Autowired
    private ActionLookup actionLookup;

    @RequestMapping(value = "/call", produces = "application/xml")
    @ResponseBody
    public String handleIncomingCall(@RequestParam("From") String from) {

        Action action = actionLookup.getActionForNumber(from);
        return action.generateTwiml();

    }
}