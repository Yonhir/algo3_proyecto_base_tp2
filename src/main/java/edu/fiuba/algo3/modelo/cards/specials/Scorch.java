package edu.fiuba.algo3.modelo.cards.specials;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.sections.Section;
import edu.fiuba.algo3.modelo.sections.SpecialZone;
import edu.fiuba.algo3.modelo.sections.rows.Row;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.List;

public class Scorch extends Special {
    public Scorch(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.applyScorchInCloseCombat(this);
        specialZone.applyScorchInRanged(this);
        specialZone.applyScorchInSiege(this);
    }

    public Unit findStrongestCard(Unit card, Unit max) {
        return card.strongerThan(max);
    }

    public void burnStrongestCardFrom(Card card, Row row) {
        row.deleteCard(card);
    }
}
