package edu.fiuba.algo3.modelo.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class AbstractJsonLoader {
    
    protected final JSONParser jsonParser;
    
    public AbstractJsonLoader() {
        this.jsonParser = new JSONParser();
    }
    
    public abstract <T> T loadFromResource(String resourcePath);
    
    protected JSONArray parseJsonArrayFromResource(String resourcePath) throws IOException, ParseException {
        Object parsed = parseJsonFromResource(resourcePath);
        return (JSONArray) parsed;
    }
    
    protected JSONObject parseJsonObjectFromResource(String resourcePath) throws IOException, ParseException {
        Object parsed = parseJsonFromResource(resourcePath);
        return (JSONObject) parsed;
    }
    
    protected Object parseJsonFromResource(String resourcePath) throws IOException, ParseException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader jsonBuffer = new BufferedReader(streamReader);
        
        return jsonParser.parse(jsonBuffer);
    }
}
