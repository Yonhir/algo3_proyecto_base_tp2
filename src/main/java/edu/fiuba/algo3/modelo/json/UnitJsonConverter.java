package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.units.modifiers.*;
import edu.fiuba.algo3.modelo.sections.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UnitJsonConverter {
    
    public List<Unit> convertFromJsonArray(JSONArray jsonArray) {
        List<Unit> units = new ArrayList<>();
        
        for (Object obj : jsonArray) {
            JSONObject jsonUnit = (JSONObject) obj;
            Unit unit = convertFromJsonObject(jsonUnit);
            units.add(unit);
        }
        
        return units;
    }
    
    private Unit convertFromJsonObject(JSONObject jsonUnit) {
        String name = (String) jsonUnit.get("nombre");
        Long pointsLong = (Long) jsonUnit.get("puntos");
        int points = pointsLong.intValue();
        
        // Parse modifiers
        List<Modifier> modifiers = parseModifiers((JSONArray) jsonUnit.get("modificador"));
        
        // Parse section types
        List<SectionType> sectionTypes = parseSectionTypes((String) jsonUnit.get("seccion"));
        
        // Create unit with empty description (not provided in JSON)
        return new Unit(name, "", points, sectionTypes, modifiers);
    }
    
    private List<Modifier> parseModifiers(JSONArray modifiersArray) {
        List<Modifier> modifiers = new ArrayList<>();
        
        for (Object modifierObj : modifiersArray) {
            String modifierName = (String) modifierObj;
            Modifier modifier = createModifier(modifierName);
            modifiers.add(modifier);
        }
        
        return modifiers;
    }
    
    private Modifier createModifier(String modifierName) {
        switch (modifierName) {
            case "Agil":
                return new Agile();
            case "Legendaria":
                return new Hero();
            case "Medico":
                return new Medic();
            case "Carta Unida":
                return new TightBond();
            case "Espia":
                // Spy requires specific parameters (Deck, Hand, Row) which we don't have here
                // For now, we'll skip it since it needs proper context
                return new Agile();
            case "Morale Boost":
            case "Morale boost":
            case "Impulso de moral":
                return new MoraleBoostModifier();
            case "Quemar":
                return new ScorchModifier();
            default:
                throw new IllegalArgumentException("Invalid modifier: " + modifierName);
        }
    }
    
    private List<SectionType> parseSectionTypes(String sectionString) {
        List<SectionType> sectionTypes = new ArrayList<>();
        List<String> sectionStrings = splitSectionString(sectionString);
        
        for (String section : sectionStrings) {
            SectionType sectionType = parseSingleSectionType(section);
            sectionTypes.add(sectionType);
        }
        
        return sectionTypes;
    }
    
    private List<String> splitSectionString(String sectionString) {
        List<String> sections = new ArrayList<>();
        
        String[] parts = sectionString.split(",", -1);
        for (String part : parts) {
            String trimmedPart = part.trim();
            sections.add(trimmedPart);
        }
        
        return sections;
    }
    
    private SectionType parseSingleSectionType(String sectionString) {
        switch (sectionString) {
            case "Cuerpo a Cuerpo":
                return new CloseCombatType();
            case "Rango":
                return new RangedType();
            case "Asedio":
                return new SiegeType();
            default:
                throw new IllegalArgumentException("Invalid section type: " + sectionString);
        }
    }
}
