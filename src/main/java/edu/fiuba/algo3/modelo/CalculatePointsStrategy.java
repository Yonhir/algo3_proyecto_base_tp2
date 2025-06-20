package edu.fiuba.algo3.modelo;

interface CalculatePointsStrategy {
    void playIn(Section section, Unit unit);

    void affectPointsFromWith(Unit unit, int points);
}
