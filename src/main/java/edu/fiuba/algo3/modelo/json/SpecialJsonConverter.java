package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.specials.MoraleBoost;
import edu.fiuba.algo3.modelo.cards.specials.ScorchCard;
import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import edu.fiuba.algo3.modelo.sections.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpecialJsonConverter {
    
    public List<Special> convertFromJsonArray(JSONArray jsonArray) {
        List<Special> specials = new ArrayList<>();
        
        for (Object obj : jsonArray) {
            JSONObject jsonSpecial = (JSONObject) obj;
            Special special = convertFromJsonObject(jsonSpecial);
            specials.add(special);
        }
        
        return specials;
    }
    
    private Special convertFromJsonObject(JSONObject jsonSpecial) {
        String name = (String) jsonSpecial.get("nombre");
        String description = (String) jsonSpecial.get("descripcion");
        String type = (String) jsonSpecial.get("tipo");
        
        return createSpecial(name, description, type, jsonSpecial);
    }
    
    private Special createSpecial(String name, String description, String type, JSONObject jsonSpecial) {
        switch (type) {
            case "Tierra arrasada":
                return new ScorchCard(name, description);
            case "Morale boost":
                return new MoraleBoost(name, description, List.of(new SpecialType()));
            case "Clima":
                return createWeatherCard(name, description, jsonSpecial);
            default:
                throw new IllegalArgumentException("Invalid special card type: " + type);
        }
    }
    
    private Weather createWeatherCard(String name, String description, JSONObject jsonSpecial) {
        JSONArray affectedSections = (JSONArray) jsonSpecial.get("afectado");
        List<String> sections = parseAffectedSections(affectedSections);
        
        switch (name) {
            case "Escarcha mordaz":
                return new BitingFrost(name, description);
            case "Niebla impenetrable":
                return new ImpenetrableFog(name, description);
            case "Lluvia torrencial":
                return new TorrentialRain(name, description);
            case "Tiempo despejado":
                return new ClearWeather(name, description);
            case "Tormeta de Skellige":
                return new SkelligeStorm(name, description);
            default:
                throw new IllegalArgumentException("Unknown weather card: " + name);
        }
    }
    
    private List<String> parseAffectedSections(JSONArray affectedSections) {
        List<String> sections = new ArrayList<>();
        
        for (Object sectionObj : affectedSections) {
            String section = (String) sectionObj;
            sections.add(section);
        }
        
        return sections;
    }
}