package edu.fiuba.algo3.modelo.sections.rows;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.sections.types.CloseCombatType;

public class CloseCombat extends Row {
    public CloseCombat(DiscardPile discardPile) {
        super(new CloseCombatType(), discardPile);
    }
}
