package edu.fiuba.algo3.modelo;

public class TorrentialRain extends Weather {
    public TorrentialRain(String name, String description) {
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