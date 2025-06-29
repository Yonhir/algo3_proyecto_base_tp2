package edu.fiuba.algo3.models.json.cards;

import edu.fiuba.algo3.models.json.sections.types.SectionTypeJsonConverter;
import edu.fiuba.algo3.models.sections.types.SectionType;
import org.json.simple.JSONObject;

import java.util.List;

public abstract class CardJsonConverter {
    
    private final SectionTypeJsonConverter sectionTypeConverter;
    
    public CardJsonConverter() {
        this.sectionTypeConverter = new SectionTypeJsonConverter();
    }
    
    protected String getName(JSONObject jsonCard) {
        return (String) jsonCard.get("nombre");
    }
    
    protected String getDescription(JSONObject jsonCard) {
        return (String) jsonCard.get("descripcion");
    }
    
    protected List<SectionType> getSectionTypes(JSONObject jsonCard) {
        String sectionString = (String) jsonCard.get("seccion");
        return sectionTypeConverter.convertFromString(sectionString);
    }
}
