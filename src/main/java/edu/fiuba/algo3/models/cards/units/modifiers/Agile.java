package edu.fiuba.algo3.models.cards.units.modifiers;

import edu.fiuba.algo3.models.sections.rows.Row;

public class Agile implements Modifier{
    @Override
    public void apply(Row row) {}
    
    @Override
    public String getDescription() {
        return "Ágil: Se pueden ubicar en dos o más secciones";
    }
}
