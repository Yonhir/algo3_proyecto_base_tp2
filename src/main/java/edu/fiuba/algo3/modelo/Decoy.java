package edu.fiuba.algo3.modelo;

public class Decoy extends Special {
    public Decoy(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean canBePlaced(Row row) {
        return false;
    }

    @Override
    public void play(Row row) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}