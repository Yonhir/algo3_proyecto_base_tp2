package edu.fiuba.algo3.modelo;

public class Siege extends Row {

    @Override
    public boolean puedeColocarUnidad(Unit unit) {
        return unit.puedeIrEnSiege();
    }

}
