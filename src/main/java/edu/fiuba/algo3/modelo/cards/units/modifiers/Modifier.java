package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.Colors.PlayerColor;
import edu.fiuba.algo3.modelo.sections.rows.Row;

public interface Modifier {
    void apply(Row row);

    default void setColor(Unit unit, PlayerColor playerColor){
        unit.setColor(playerColor);
    }
}
