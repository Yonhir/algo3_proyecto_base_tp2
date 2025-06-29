package edu.fiuba.algo3.models.sections.rows;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.types.SiegeType;

public class Siege extends Row {
    public Siege(DiscardPile discardPile) {
        super(new SiegeType(), discardPile);
    }
}
