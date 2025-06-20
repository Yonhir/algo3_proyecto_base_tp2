package edu.fiuba.algo3.modelo;

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
}
