package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.sections.rows.Row;

public class Hero implements Modifier {
    @Override
    public void apply(Row row) {

    }
    
    @Override
    public String getDescription() {
        return "Héroe: No puede ser afectado por ningún efecto";
    }
}
