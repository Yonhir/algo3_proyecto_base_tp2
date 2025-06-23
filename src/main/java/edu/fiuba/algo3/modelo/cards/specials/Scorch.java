package edu.fiuba.algo3.modelo.cards.specials;

import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.List;

public class Scorch extends Special {
    private Unit strongestCard;

    public Scorch(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyInAllRows(this);
    }

    public void burnStrongestCardFrom(Card card, Row row) {
        row.discardCard(card);
    }

    public void saveStrongest(Unit unit) {
        if (this.strongestCard == null) {
            this.strongestCard = unit;
        } else {
            this.strongestCard = unit.strongerThan(strongestCard);
        }
    }

    public boolean matchesStrongest(Unit unit) {
        return unit.samePointsAs(strongestCard);
    }
}
