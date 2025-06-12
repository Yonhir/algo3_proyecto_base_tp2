package edu.fiuba.algo3.modelo;

public abstract class Weather extends Special {
    public Weather(String name, String description) {
        super(name, description);
    }

    @Override
    public void play(CardTarget target) {
        target.addCard(this);
    }

    public abstract void apply(Card card, Row row);

    public abstract void addToWeatherZone(SpecialZone specialZone);

    @Override
    public boolean canBePlaced(CardTarget target) {
        return target instanceof SpecialZone;
    }
}
