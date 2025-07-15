package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.types.RangedType;

public class UIRanged extends UIRow {

    public UIRanged(Ranged row) {
        super(row);
    }

    protected void setType() {
        type = new RangedType();
    }
}
