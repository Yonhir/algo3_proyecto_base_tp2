package edu.fiuba.algo3.modelo.sections.rows;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.sections.types.SiegeType;

public class Siege extends Row {
    public Siege(DiscardPile discardPile) {
        super(new SiegeType(), discardPile);
    }
}
