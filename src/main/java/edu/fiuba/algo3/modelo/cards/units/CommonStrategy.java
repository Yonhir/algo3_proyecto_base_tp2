package edu.fiuba.algo3.modelo.cards.units;

import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.rows.Row;

public class CommonStrategy implements CalculatePointsStrategy {
    @Override
    public void playIn(Section section, Unit unit) {
        Row row = (Row) section;
        row.addCard(unit);
    }

    @Override
    public void affectPointsFromWith(Unit unit, int points) {
        unit.affectPointsWith(points);
    }
}
