package edu.fiuba.algo3.modelo;

import java.util.List;

public class Siege extends Row {

    @Override
    public boolean puedeColocarUnidad(Unit unit) {
        return unit.puedeIrEnSiege();
    }

    @Override
    public void placeCard(Card card) {
        validateCard(card);
        card.play(this);
    }

}
