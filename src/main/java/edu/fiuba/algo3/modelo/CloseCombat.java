package edu.fiuba.algo3.modelo;

public class CloseCombat extends Row {

    @Override
    public boolean canBePlacedIn(Unit unit) {
        return unit.canBeInCloseCombat();
    }
}
