package edu.fiuba.algo3.modelo;

public class Ranged extends Row {

    @Override
    public boolean puedeColocarUnidad(Unit unit) {
        return unit.puedeIrEnRanged();
    }

}
