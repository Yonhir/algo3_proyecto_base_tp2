package edu.fiuba.algo3.models.json.sections.types;

import edu.fiuba.algo3.models.sections.types.CloseCombatType;
import edu.fiuba.algo3.models.sections.types.RangedType;
import edu.fiuba.algo3.models.sections.types.SectionType;
import edu.fiuba.algo3.models.sections.types.SiegeType;

import java.util.ArrayList;
import java.util.List;

public class SectionTypeJsonConverter {
    
    public List<SectionType> convertFromString(String sectionString) {
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
        
        String[] parts = sectionString.split(",");
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