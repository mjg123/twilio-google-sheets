package com.example.gsheetsintegration;

import com.example.gsheetsintegration.actions.Action;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ActionLookup {

    @Value("${google.sheet.id}")
    private String SHEET_ID;

    @Autowired
    private Sheets sheetsService;

    private Map<String, Action> parseGoogleSheetsData(List<List<Object>> rawData) {
        return rawData.stream()
            .skip(1) // skip the row with column headings
            .collect(Collectors.toMap(
                row -> Objects.toString(row.get(0), null),
                row -> Action.create(
                    Objects.toString(row.get(1), null),
                    Objects.toString(row.get(2), null))));
    }

    private Map<String, Action> createActionMap() throws IOException {
        String range = "CallFilters!A:C";

        ValueRange response = sheetsService.spreadsheets().values()
            .get(SHEET_ID, range)
            .execute();

        List<List<Object>> values = response.getValues();

        return parseGoogleSheetsData(values);
    }

    private Action getActionForNumberFromSheet(String from) throws IOException {
        Map<String, Action> actionMap = createActionMap();
        Action action = actionMap.get(from);

        if (action != null) {
            return action;
        }

        // If we get here, the number isn't present in the sheet.
        // Look in the sheet for an action called "Default"
        // and if _that_ isn't present, return the hard-coded default
        return actionMap.getOrDefault("Default", Action.DEFAULT);
    }

    public Action getActionForNumber(String from) {
        try {
            return getActionForNumberFromSheet(from);

        } catch (Exception e) {
            // in case there is an error fetching data from google
            return Action.DEFAULT;
        }
    }

}
