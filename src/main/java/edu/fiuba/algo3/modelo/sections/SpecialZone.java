package edu.fiuba.algo3.modelo.sections;

import edu.fiuba.algo3.modelo.Round;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.types.SpecialType;

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
    public void placeCard(Card card, Round round) {
        card.verifySectionType(this.sectionType);
        card.play(this);
        round.playerPlayedCard();
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