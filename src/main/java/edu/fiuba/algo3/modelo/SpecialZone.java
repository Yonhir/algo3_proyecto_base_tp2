package edu.fiuba.algo3.modelo;

import java.util.List;

public class SpecialZone implements Section {
    private final List<Row> closeCombatRows;
    private final List<Row> rangedRows;
    private final List<Row> siegeRows;
    private final SpecialType sectionType;

    public SpecialZone(List<Row> closeCombatRows, List<Row> rangedRows, List<Row> siegeRows) {
        this.closeCombatRows = closeCombatRows;
        this.rangedRows = rangedRows;
        this.siegeRows = siegeRows;
        this.sectionType = new SpecialType();
    }

    @Override
    public void placeCard(Card card) {
        card.verifySectionType(this.sectionType);
        card.play(this);
    }

    public void applyCloseCombatWeather(Weather weather) {
        for (Row row : closeCombatRows) {
            row.applyWeather(weather);
        }
    }

    public void applyRangedWeather(Weather weather) {
        for (Row row : rangedRows) {
            row.applyWeather(weather);
        }
    }

    public void applySiegeWeather(Weather weather) {
        for (Row row : siegeRows) {
            row.applyWeather(weather);
        }
    }
} 