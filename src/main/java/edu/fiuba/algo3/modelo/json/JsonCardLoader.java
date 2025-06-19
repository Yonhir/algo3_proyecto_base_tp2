package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.errors.GwentFileInvalid;
import edu.fiuba.algo3.modelo.errors.SpecialsFileInvalid;
import edu.fiuba.algo3.modelo.errors.UnitsFileInvalid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonCardLoader {
 
    private final JSONParser jsonParser;
    private final UnitJsonConverter unitConverter;
    private final SpecialJsonConverter specialConverter;
    private final GameJsonConverter gameConverter;

    public JsonCardLoader() {
        this.jsonParser = new JSONParser();
        this.unitConverter = new UnitJsonConverter();
        this.specialConverter = new SpecialJsonConverter();
        this.gameConverter = new GameJsonConverter();
    }

    // ============================================================================
    // PUBLIC API METHODS
    // ============================================================================
    
    public List<Unit> loadUnitsFromResource(String resourcePath) {
        try {
            JSONArray jsonUnits = parseJsonArrayFromResource(resourcePath);
            return unitConverter.convertFromJsonArray(jsonUnits);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            // File reading, parsing, or structure errors
            // print traceback of the error
            e.printStackTrace();
            throw new UnitsFileInvalid("Error reading or parsing file");
        } catch (RuntimeException e) {
            // Data conversion errors
            throw new UnitsFileInvalid("Error converting data");
        }
    }
    
    public List<Special> loadSpecialsFromResource(String resourcePath) {
        try {
            JSONArray jsonSpecials = parseJsonArrayFromResource(resourcePath);
            return specialConverter.convertFromJsonArray(jsonSpecials);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            // File reading, parsing, or structure errors
            throw new SpecialsFileInvalid("Error reading or parsing file");
        } catch (RuntimeException e) {
            // Data conversion errors
            throw new SpecialsFileInvalid("Error converting data");
        }
    }
    
    public List<Deck> loadGameFromResource(String resourcePath) {
        try {
            JSONObject jsonGame = parseJsonObjectFromResource(resourcePath);
            return gameConverter.convertFromJsonObject(jsonGame);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            // File reading, parsing, or structure errors
            throw new GwentFileInvalid("Error reading or parsing file");
        } catch (RuntimeException e) {
            // Data conversion errors
            throw new GwentFileInvalid("Error converting data");
        }
    }

    // ============================================================================
    // PRIVATE JSON PARSING METHODS
    // ============================================================================
    
    private JSONArray parseJsonArrayFromResource(String resourcePath) throws IOException, ParseException {
        Object parsed = parseJsonFromResource(resourcePath);
        JSONArray jsonArray = (JSONArray) parsed;
        return jsonArray;
    }
    
    private JSONObject parseJsonObjectFromResource(String resourcePath) throws IOException, ParseException {
        Object parsed = parseJsonFromResource(resourcePath);
        JSONObject jsonObject = (JSONObject) parsed;
        return jsonObject;
    }

    private Object parseJsonFromResource(String resourcePath) throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader jsonBuffer = new BufferedReader(streamReader);
        
        return jsonParser.parse(jsonBuffer);
    }
}