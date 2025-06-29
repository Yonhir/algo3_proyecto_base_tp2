package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.Section;
import edu.fiuba.algo3.models.sections.types.SpecialType;
import edu.fiuba.algo3.models.sections.SpecialZone;

import java.util.List;

public class ClearWeather extends Weather {
    public ClearWeather(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.addCard(this);
        specialZone.applyCloseCombatWeather(this);
        specialZone.applyRangedWeather(this);
        specialZone.applySiegeWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
    }
}
