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