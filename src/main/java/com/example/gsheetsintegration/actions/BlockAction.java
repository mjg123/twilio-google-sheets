package com.example.gsheetsintegration.actions;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;

public class BlockAction implements Action {

    private final String message;

    public BlockAction(String message) {
        this.message = message;
    }

    @Override
    public String generateTwiml() {
        return new VoiceResponse.Builder().say(
            new Say.Builder(message).build()
        ).build().toXml();
    }
}
