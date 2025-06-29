package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.*;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.types.SpecialType;

import java.util.List;

public class TorrentialRain extends Weather {
    // Sets the strength of all Siege Combat cards to 1 for both players.

    public TorrentialRain(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.addCard(this);
        specialZone.applySiegeWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof Siege) {
            ((Unit) card).setPoints(1);
        }
    }
}
