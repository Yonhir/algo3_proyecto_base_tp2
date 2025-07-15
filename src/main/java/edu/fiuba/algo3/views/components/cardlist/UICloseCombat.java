package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.types.CloseCombatType;

public class UICloseCombat extends UIRow {

    public UICloseCombat(CloseCombat row) {
        super(row);
    }
    protected void setType() {
        type = new CloseCombatType();
    }
}
