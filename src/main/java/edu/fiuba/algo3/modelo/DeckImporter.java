package edu.fiuba.algo3.modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeckImporter {

    public static Deck importDeckFromJson(String filePath) throws IOException, ParseException {
        List<Card> cards = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject deckJson = (JSONObject) parser.parse(reader);
            
            // Import units
            JSONArray unitsArray = (JSONArray) deckJson.get("units");
            if (unitsArray != null) {
                for (Object obj : unitsArray) {
                    JSONObject unitJson = (JSONObject) obj;
                    
                    String name = (String) unitJson.get("name");
                    String description = (String) unitJson.get("description");
                    int points = ((Long) unitJson.get("points")).intValue();
                    boolean closeCombat = (Boolean) unitJson.get("closeCombat");
                    boolean ranged = (Boolean) unitJson.get("ranged");
                    boolean siege = (Boolean) unitJson.get("siege");
                    
                    // Process modifiers
                    List<Modifier> modifiers = new ArrayList<>();
                    JSONArray modifiersArray = (JSONArray) unitJson.get("modifiers");
                    
                    if (modifiersArray != null) {
                        for (Object modObj : modifiersArray) {
                            String modifierName = (String) modObj;
                            if ("Agil".equals(modifierName)) {
                                modifiers.add(new Agil());
                            } else if ("TightBond".equals(modifierName)) {
                                modifiers.add(new TightBond());
                            }
                        }
                    }
                    
                    Unit unit = new Unit(name, description, points, closeCombat, ranged, siege, modifiers);
                    cards.add(unit);
                }
            }
            
            // Import special cards
            JSONArray specialsArray = (JSONArray) deckJson.get("specials");
            if (specialsArray != null) {
                for (Object obj : specialsArray) {
                    JSONObject specialJson = (JSONObject) obj;
                    
                    String name = (String) specialJson.get("name");
                    String description = (String) specialJson.get("description");
                    String type = (String) specialJson.get("type");
                    
                    Card specialCard = null;
                    
                    // Create the appropriate special card based on type
                    switch (type) {
                        case "BitingFrost":
                            specialCard = new BitingFrost(name, description);
                            break;
                        case "TorrentialRain":
                            specialCard = new TorrentialRain(name, description);
                            break;
                        case "ImpenetrableFog":
                            specialCard = new ImpenetrableFog(name, description);
                            break;
                        case "ClearWeather":
                            specialCard = new ClearWeather(name, description);
                            break;
                    }
                    
                    if (specialCard != null) {
                        cards.add(specialCard);
                    }
                }
            }
        }
        
        // Create and return the deck
        return new Deck(cards);
    }
} 