package edu.fiuba.algo3.modelo;

public class TorrentialRain extends Weather {
    // Sets the strength of all Siege Combat cards to 1 for both players.

    public TorrentialRain(String name, String description) {
        super(name, description);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Siege) {
            ((Unit) card).setPoints(1);
        }
    }

    @Override
    public void addToWeatherZone(SpecialZone specialZone) {
        specialZone.addSiegeWeather(this);
    }
}
