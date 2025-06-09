package edu.fiuba.algo3.modelo;

public class TacticalAdvantage extends Special {
    public TacticalAdvantage(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean puedeSerColocadaEn(Row row) {
        return false;
    }

    @Override
    public void play(Row row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}