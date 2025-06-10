package edu.fiuba.algo3.modelo;

public class ClearWeather extends Weather {
    public ClearWeather(String name, String description) {
        super(name, description);
    }

    @Override
    public void play(CardTarget target) {
        if (target instanceof WeatherZone) {
            WeatherZone weatherZone = (WeatherZone) target;
            weatherZone.addCard(this);
        } else {
            throw new IllegalArgumentException("ClearWeather can only be placed in a WeatherZone");
        }
    }

    @Override
    public void addToWeatherZone(WeatherZone weatherZone) {
        weatherZone.addCloseCombatWeather(this);
        weatherZone.addRangedWeather(this);
        weatherZone.addSiegeWeather(this);
    }

    @Override
    public boolean canBePlaced(CardTarget target) {
        return target instanceof WeatherZone;
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
    }
}
