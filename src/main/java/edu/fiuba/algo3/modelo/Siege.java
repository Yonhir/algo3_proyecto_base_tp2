package edu.fiuba.algo3.modelo;

public class Siege extends Row {

    @Override
    public boolean canBePlacedIn(Unit unit) {
        return unit.canBeInSiege();
    }

}
