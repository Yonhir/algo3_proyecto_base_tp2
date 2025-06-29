package edu.fiuba.algo3.models.sections.rows;

import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;

public class CloseCombat extends Row {
    public CloseCombat(DiscardPile discardPile) {
        super(new CloseCombatType(), discardPile);
    }
}
