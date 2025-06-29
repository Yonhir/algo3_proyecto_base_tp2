package edu.fiuba.algo3.models.json;

import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.errors.UnitsFileInvalid;
import edu.fiuba.algo3.models.json.cards.units.UnitJsonConverter;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class UnitsLoader extends AbstractJsonLoader {
    
    private final UnitJsonConverter unitConverter;
    
    public UnitsLoader() {
        this.unitConverter = new UnitJsonConverter();
    }

    public List<Unit> loadFromResource(String resourcePath, 
                                     Deck playerDeck, Hand playerHand, DiscardPile playerDiscardPile) {
        JSONArray jsonUnits = null;
        List<Unit> units = null;

        try {
            jsonUnits = parseJsonArrayFromResource(resourcePath);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            throw new UnitsFileInvalid("Error reading or parsing file");
        }

        try {
            units = unitConverter.convertFromJsonArray(jsonUnits, playerDeck, playerHand, playerDiscardPile);
        } catch (RuntimeException e) {
            throw new UnitsFileInvalid("Error converting data");
        }

        return units;
    }
}
