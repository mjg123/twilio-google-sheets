package com.example.gsheetsintegration.actions;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Dial;

public class ForwardAction implements Action {

    private final String number;

    public ForwardAction(String number) {
        this.number = number;
    }

    @Override
    public String generateTwiml() {
        return new VoiceResponse.Builder().dial(
            new Dial.Builder(number).build()
        ).build().toXml();
    }
}
