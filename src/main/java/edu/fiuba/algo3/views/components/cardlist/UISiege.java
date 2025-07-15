package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.SiegeType;

public class UISiege extends UIRow {

    public UISiege(Siege row) {
        super(row);
    }
    protected void setType() {
        type = new SiegeType();
    }
}
