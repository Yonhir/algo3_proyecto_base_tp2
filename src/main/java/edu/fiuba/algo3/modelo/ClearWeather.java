package edu.fiuba.algo3.modelo;

public class ClearWeather extends Weather {
    public ClearWeather(String name, String description) {
        super(name, description);
    }

    @Override
    public void play(CardTarget target) {
        if (target instanceof SpecialZone) {
            SpecialZone specialZone = (SpecialZone) target;
            specialZone.addCard(this);
        } else {
            throw new IllegalArgumentException("ClearWeather can only be placed in a WeatherZone");
        }
    }

    @Override
    public void addToSpecialZone(SpecialZone specialZone) {
        specialZone.addCloseCombatWeather(this);
        specialZone.addRangedWeather(this);
        specialZone.addSiegeWeather(this);
    }

    @Override
    public boolean canBePlaced(CardTarget target) {
        return target instanceof SpecialZone;
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
    }
}
