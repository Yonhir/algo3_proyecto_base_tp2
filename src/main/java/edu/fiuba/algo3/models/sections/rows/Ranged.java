package edu.fiuba.algo3.models.sections.rows;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.types.RangedType;

public class Ranged extends Row {
    public Ranged(DiscardPile discardPile) {
        super(new RangedType(), discardPile);
    }
}
