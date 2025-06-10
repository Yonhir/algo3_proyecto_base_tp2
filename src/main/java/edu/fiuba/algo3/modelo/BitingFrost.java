package edu.fiuba.algo3.modelo;

public class BitingFrost extends Weather {
    // Sets the strength of all Close Combat cards to 1 for both players.

    public BitingFrost(String name, String description) {
        super(name, description);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof CloseCombat) {
            ((Unit) card).setPoints(1);
        }
    }

    @Override
    public void addToWeatherZone(WeatherZone weatherZone) {
        weatherZone.addCloseCombatWeather(this);
    }
}
