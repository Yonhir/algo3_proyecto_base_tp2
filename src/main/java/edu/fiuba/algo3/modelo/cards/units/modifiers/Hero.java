package edu.fiuba.algo3.modelo.cards.units.modifiers;

import edu.fiuba.algo3.modelo.sections.Row;

public class Hero implements Modifier {
    @Override
    public void apply(Row row) {
        // Hero cards are not affected by other modifiers, they have fixed points
    }
} 