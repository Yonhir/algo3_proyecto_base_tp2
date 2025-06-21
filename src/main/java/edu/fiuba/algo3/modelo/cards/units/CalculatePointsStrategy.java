package edu.fiuba.algo3.modelo.cards.units;

import edu.fiuba.algo3.modelo.sections.Section;

interface CalculatePointsStrategy {
    void playIn(Section section, Unit unit);

    void affectPointsFromWith(Unit unit, int points);

    Unit chooseStronger(Unit max, Unit actual);
}
