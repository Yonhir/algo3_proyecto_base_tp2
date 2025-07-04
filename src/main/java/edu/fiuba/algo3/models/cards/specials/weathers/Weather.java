package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.types.SectionType;

import java.util.List;

public abstract class Weather extends Special {
    public Weather(String name, String description, List<SectionType> sectionTypes) {
        super(name, description, sectionTypes);
    }

    public abstract void apply(Card card, Row row);
}
