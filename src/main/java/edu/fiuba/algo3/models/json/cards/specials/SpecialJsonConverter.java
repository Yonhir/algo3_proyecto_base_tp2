package edu.fiuba.algo3.models.json.cards.specials;

import edu.fiuba.algo3.models.cards.specials.Scorch;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.cards.specials.MoraleBoost;
import edu.fiuba.algo3.models.json.cards.CardJsonConverter;
import edu.fiuba.algo3.models.json.cards.specials.weathers.WeatherJsonConverter;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SiegeType;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SpecialJsonConverter extends CardJsonConverter {
    
    private final WeatherJsonConverter weatherConverter;
    
    public SpecialJsonConverter() {
        this.weatherConverter = new WeatherJsonConverter();
    }
    
    public List<Special> convertFromJsonArray(JSONArray jsonArray) {
        List<Special> specials = new ArrayList<>();
        
        for (Object obj : jsonArray) {
            JSONObject jsonSpecial = (JSONObject) obj;
            Special special = convertFromJsonObject(jsonSpecial);
            specials.add(special);
        }
        
        return specials;
    }
    
    public Special convertFromJsonObject(JSONObject jsonSpecial) {
        String name = getName(jsonSpecial);
        String description = getDescription(jsonSpecial);
        
        String type = getType(jsonSpecial);
        
        return createSpecial(name, description, type, jsonSpecial);
    }
    
    protected String getType(JSONObject jsonSpecial) {
        return (String) jsonSpecial.get("tipo");
    }
    
    private Special createSpecial(String name, String description, String type, JSONObject jsonSpecial) {
        switch (type) {
            case "Tierra arrasada":
                return new Scorch(name, description, List.of(new SpecialType()));
            case "Morale boost":
                return new MoraleBoost(name, description, List.of(new CloseCombatType(), new RangedType(), new SiegeType()));
            case "Clima":
                return weatherConverter.createWeatherCard(name, description, jsonSpecial);
            default:
                throw new IllegalArgumentException("Invalid special card type: " + type);
        }
    }
}
