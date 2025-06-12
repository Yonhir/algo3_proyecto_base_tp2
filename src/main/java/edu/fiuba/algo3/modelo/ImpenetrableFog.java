package edu.fiuba.algo3.modelo;

public class ImpenetrableFog extends Weather {
    // Sets the strength of all Ranged cards to 1 for both players.
    
    public ImpenetrableFog(String name, String description) {
        super(name, description);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Ranged) {
            ((Unit) card).setPoints(1);
        }
    }

    @Override
    public void addToWeatherZone(SpecialZone specialZone) {
        specialZone.addRangedWeather(this);
    }
}
