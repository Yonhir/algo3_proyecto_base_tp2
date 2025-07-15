package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.sections.rows.Row;

public interface Modifier {
    void apply(Row row);
    String getDescription();
}
