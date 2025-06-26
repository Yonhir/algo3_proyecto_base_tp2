package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.errors.SpecialsFileInvalid;
import edu.fiuba.algo3.modelo.json.cards.specials.SpecialJsonConverter;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class SpecialsLoader extends AbstractJsonLoader {
    
    private final SpecialJsonConverter specialConverter;
    
    public SpecialsLoader() {
        this.specialConverter = new SpecialJsonConverter();
    }
    
    public List<Special> loadFromResource(String resourcePath) {
        JSONArray jsonSpecials = null;
        List<Special> specials = null;

        try {
            jsonSpecials = parseJsonArrayFromResource(resourcePath);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            throw new SpecialsFileInvalid("Error reading or parsing file");
        }

        try {
            specials = specialConverter.convertFromJsonArray(jsonSpecials);
        } catch (RuntimeException e) {
            throw new SpecialsFileInvalid("Error converting data");
        }

        return specials;
    }
}
