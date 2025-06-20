package edu.fiuba.algo3.modelo.cards.specials;

import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.sections.types.SectionType;

import java.util.List;

public abstract class Special extends Card {
    public Special(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }
}
