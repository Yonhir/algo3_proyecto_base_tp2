package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.UnitsFileInvalid;
import edu.fiuba.algo3.modelo.json.cards.units.UnitJsonConverter;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class UnitsLoader extends AbstractJsonLoader {
    
    private final UnitJsonConverter unitConverter;
    
    public UnitsLoader() {
        this.unitConverter = new UnitJsonConverter();
    }
    
    @Override
    public List<Unit> loadFromResource(String resourcePath) {
        JSONArray jsonUnits = null;
        List<Unit> units = null;

        try {
            jsonUnits = parseJsonArrayFromResource(resourcePath);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            throw new UnitsFileInvalid("Error reading or parsing file");
        }

        try {
            units = unitConverter.convertFromJsonArray(jsonUnits);
        } catch (RuntimeException e) {
            throw new UnitsFileInvalid("Error converting data");
        }

        return units;
    }
}
