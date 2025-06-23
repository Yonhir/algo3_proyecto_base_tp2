package edu.fiuba.algo3.modelo.sections.rows;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.sections.types.RangedType;

public class Ranged extends Row {
    public Ranged(DiscardPile discardPile) {
        super(new RangedType(), discardPile);
    }
}
