package edu.fiuba.algo3.modelo;

public class CloseCombat extends Row {

    @Override
    public boolean puedeColocarUnidad(Unit unit) {
        return unit.puedeIrEnCloseCombat();
    }
}
