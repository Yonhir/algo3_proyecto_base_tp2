package edu.fiuba.algo3.modelo;

public class Scorch extends Special {
    public Scorch(String name, String description) {
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