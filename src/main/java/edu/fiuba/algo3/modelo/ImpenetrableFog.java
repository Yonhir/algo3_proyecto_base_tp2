package edu.fiuba.algo3.modelo;

public class ImpenetrableFog extends Weather {
    public ImpenetrableFog(String name, String description) {
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