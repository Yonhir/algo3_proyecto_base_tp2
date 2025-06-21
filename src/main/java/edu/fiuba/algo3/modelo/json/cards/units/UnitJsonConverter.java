package edu.fiuba.algo3.modelo.json.cards.units;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.Modifier;
import edu.fiuba.algo3.modelo.json.cards.CardJsonConverter;
import edu.fiuba.algo3.modelo.json.cards.units.modifiers.ModifierJsonConverter;
import edu.fiuba.algo3.modelo.sections.types.SectionType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnitJsonConverter extends CardJsonConverter {
    
    private final ModifierJsonConverter modifierConverter;
    
    public UnitJsonConverter() {
        this.modifierConverter = new ModifierJsonConverter();
    }
    
    public List<Unit> convertFromJsonArray(JSONArray jsonArray) {
        List<Unit> units = new ArrayList<>();
        
        for (Object obj : jsonArray) {
            JSONObject jsonUnit = (JSONObject) obj;
            Unit unit = convertFromJsonObject(jsonUnit);
            units.add(unit);
        }
        
        return units;
    }
    
    public Unit convertFromJsonObject(JSONObject jsonUnit) {
        String name = getName(jsonUnit);
        String description = getDescription(jsonUnit);
        List<SectionType> sectionTypes = getSectionTypes(jsonUnit);
        
        int points = getPoints(jsonUnit);
        List<Modifier> modifiers = getModifiers(jsonUnit);
        
        return new Unit(name, description, points, sectionTypes, modifiers);
    }
    
    protected int getPoints(JSONObject jsonUnit) {
        Long pointsLong = (Long) jsonUnit.get("puntos");
        return pointsLong.intValue();
    }
    
    protected List<Modifier> getModifiers(JSONObject jsonUnit) {
        JSONArray modifiersArray = (JSONArray) jsonUnit.get("modificador");
        return modifierConverter.convertFromJsonArray(modifiersArray);
    }
}
