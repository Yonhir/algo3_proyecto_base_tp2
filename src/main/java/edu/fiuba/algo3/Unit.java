package edu.fiuba.algo3;

public class Unit extends Card {
    private int puntos;
    private RowType type;

    public Unit(String name, String description, int puntaje, RowType row) {
        super(name, description);
        puntos = puntaje;
        type = row;
    }

    public int getPuntos() {
        return puntos;
    }
}
