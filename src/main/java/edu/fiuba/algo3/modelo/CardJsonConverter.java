package edu.fiuba.algo3.modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardJsonConverter {

    @SuppressWarnings("unchecked")
    public static void exportCards(List<Unit> cards, String filePath) throws IOException {
        JSONArray cardsArray = new JSONArray();
        
        for (Unit card : cards) {
            JSONObject cardObject = new JSONObject();
            cardObject.put("name", card.getName());
            cardObject.put("description", card.getDescription());
            cardObject.put("points", card.getBasePoints());
            cardObject.put("closeCombat", card.canBeInCloseCombat());
            cardObject.put("ranged", card.canBeInRanged());
            cardObject.put("siege", card.canBeInSiege());
            
            // Save modifiers
            JSONArray modifiersArray = new JSONArray();
            if (card.haveModifier(new Agil())) {
                modifiersArray.add("Agil");
            }
            if (card.haveModifier(new TightBond())) {
                modifiersArray.add("TightBond");
            }
            cardObject.put("modifiers", modifiersArray);
            
            cardsArray.add(cardObject);
        }
        
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(cardsArray.toJSONString());
            file.flush();
        }
    }
    
    public static List<Unit> importCards(String filePath) throws IOException, ParseException {
        List<Unit> importedCards = new ArrayList<>();
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray cardsArray = (JSONArray) parser.parse(reader);
            
            for (Object obj : cardsArray) {
                JSONObject cardObject = (JSONObject) obj;
                
                String name = (String) cardObject.get("name");
                String description = (String) cardObject.get("description");
                int points = ((Long) cardObject.get("points")).intValue();
                boolean closeCombat = (Boolean) cardObject.get("closeCombat");
                boolean ranged = (Boolean) cardObject.get("ranged");
                boolean siege = (Boolean) cardObject.get("siege");
                
                // Process modifiers
                List<Modifier> modifiers = new ArrayList<>();
                JSONArray modifiersArray = (JSONArray) cardObject.get("modifiers");
                
                for (Object modObj : modifiersArray) {
                    String modifierName = (String) modObj;
                    if ("Agil".equals(modifierName)) {
                        modifiers.add(new Agil());
                    } else if ("TightBond".equals(modifierName)) {
                        modifiers.add(new TightBond());
                    }
                }
                
                Unit card = new Unit(name, description, points, closeCombat, ranged, siege, modifiers);
                importedCards.add(card);
            }
        }
        
        return importedCards;
    }
} 