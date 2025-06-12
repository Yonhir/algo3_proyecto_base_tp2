package edu.fiuba.algo3.modelo;

public class Ranged extends Row {

    @Override
    public boolean canBePlacedIn(Unit unit) {
        return unit.canBeInRanged();
    }
}
