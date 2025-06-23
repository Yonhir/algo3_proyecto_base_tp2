package edu.fiuba.algo3.modelo.sections;

import edu.fiuba.algo3.modelo.Player;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.weathers.Weather;
import edu.fiuba.algo3.modelo.colors.Green;
import edu.fiuba.algo3.modelo.colors.PlayerColor;
import edu.fiuba.algo3.modelo.sections.rows.CloseCombat;
import edu.fiuba.algo3.modelo.sections.rows.Ranged;
import edu.fiuba.algo3.modelo.sections.rows.Siege;
import edu.fiuba.algo3.modelo.sections.types.SpecialType;

import java.nio.channels.Pipe;
import java.util.List;

public class SpecialZone implements Section {
    private final List<CloseCombat> closeCombatRows;
    private final List<Ranged> rangedRows;
    private final List<Siege> siegeRows;
    private final PlayerColor green;
    private final SpecialType sectionType;

    public SpecialZone(CloseCombat aPlayerCloseCombat, Ranged aPlayerRangedRow, Siege aPlayerSiegeRow, CloseCombat otherPlayerCloseCombat, Ranged otherPlayerRangedRow, Siege otherPlayerSiegeRow) {
        if(aPlayerCloseCombat.equals(otherPlayerCloseCombat) || aPlayerRangedRow.equals(otherPlayerRangedRow) || aPlayerSiegeRow.equals(otherPlayerSiegeRow)) {
            throw new IllegalArgumentException("Close combat, ranged and siege rows must be different");
        }
        this.closeCombatRows = List.of(aPlayerCloseCombat, otherPlayerCloseCombat);
        this.rangedRows = List.of(aPlayerRangedRow, otherPlayerRangedRow);
        this.siegeRows = List.of(aPlayerSiegeRow, otherPlayerSiegeRow);
        this.sectionType = new SpecialType();
        green = new Green();
    }

    @Override
    public void placeCard(Card card) {
        card.verifySectionType(this.sectionType);
        card.verifyColor(green);
        card.play(this);
    }

    public void applyCloseCombatWeather(Weather weather) {
        for (CloseCombat row : closeCombatRows) {
            row.applyWeather(weather);
        }
    }

    public void applyRangedWeather(Weather weather) {
        for (Ranged row : rangedRows) {
            row.applyWeather(weather);
        }
    }

    public void applySiegeWeather(Weather weather) {
        for (Siege row : siegeRows) {
            row.applyWeather(weather);
        }
    }
}
