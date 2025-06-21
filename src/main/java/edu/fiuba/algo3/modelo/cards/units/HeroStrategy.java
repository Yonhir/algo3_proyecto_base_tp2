package edu.fiuba.algo3.modelo.cards.units;

import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.rows.Row;

import java.util.List;

public class HeroStrategy implements CalculatePointsStrategy {
    @Override
    public void playIn(Section section, Unit unit) {
        Row row = (Row) section;
        row.addCard(unit);
    }

    @Override
    public void affectPointsFromWith(Unit unit, int points) {
        unit.resetPoints();
    }

    @Override
    public Unit chooseStronger(Unit max, Unit actual) {
        if (max.samePointsAs(actual)) {
            return new Unit("", "", 0, List.of(), List.of(), new CommonStrategy());
        }
        return actual;
    }
}
