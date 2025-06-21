package edu.fiuba.algo3.modelo.json.cards.specials.weathers;

import edu.fiuba.algo3.modelo.cards.specials.weathers.*;
import org.json.simple.JSONObject;


public class WeatherJsonConverter {
    
    public Weather createWeatherCard(String name, String description, JSONObject jsonSpecial) {
        switch (name) {
            case "Escarcha mordaz":
                return new BitingFrost(name, description);
            case "Niebla impenetrable":
                return new ImpenetrableFog(name, description);
            case "Lluvia torrencial":
                return new TorrentialRain(name, description);
            case "Tiempo despejado":
                return new ClearWeather(name, description);
            case "Tormeta de Skellige":
                return new SkelligeStorm(name, description);
            default:
                throw new IllegalArgumentException("Unknown weather card: " + name);
        }
    }
} 