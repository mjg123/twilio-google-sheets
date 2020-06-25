package com.example.gsheetsintegration.actions;

public interface Action {

    Action DEFAULT = new BlockAction("Sorry I'm not available right now.");

    static Action create(String behaviour, String data) {

        switch (behaviour) {

            case "Block":
                return new BlockAction(data);

            case "Forward":
                return new ForwardAction(data);

            default:
                return DEFAULT;
        }
    }

    String generateTwiml();
}
