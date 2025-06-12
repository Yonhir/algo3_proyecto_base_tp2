package edu.fiuba.algo3.modelo;

import java.util.List;

public class SpecialZone implements CardTarget {
    private final List<Row> closeCombatRows;
    private final List<Row> rangedRows;
    private final List<Row> siegeRows;

    public SpecialZone(List<Row> closeCombatRows, List<Row> rangedRows, List<Row> siegeRows) {
        this.closeCombatRows = closeCombatRows;
        this.rangedRows = rangedRows;
        this.siegeRows = siegeRows;
    }

    @Override
    public void placeCard(Card card) {
        if (!card.canBePlaced(this)) {
            throw new IllegalArgumentException("La carta no puede colocarse en esta zona de clima.");
        }
        card.play(this);
    }

    @Override
    public void addCard(Card card) {
        if (card instanceof Weather) {
            ((Weather) card).addToSpecialZone(this);
        }
    }

    public void addCloseCombatWeather(Weather weather) {
        for (Row row : closeCombatRows) {
            row.addWeather(weather);
        }
    }

    public void addRangedWeather(Weather weather) {
        for (Row row : rangedRows) {
            row.addWeather(weather);
        }
    }

    public void addSiegeWeather(Weather weather) {
        for (Row row : siegeRows) {
            row.addWeather(weather);
        }
    }
} 