package edu.fiuba.algo3.models.cards.specials.weathers;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.sections.*;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.models.sections.types.SpecialType;

import java.util.List;

public class BitingFrost extends Weather {
    // Sets the strength of all Close Combat cards to 1 for both players.

    public BitingFrost(String name, String description) {
        super(name, description, List.of(new SpecialType()));
    }

    @Override
    public void play(Section section) {
        SpecialZone specialZone = (SpecialZone) section;
        specialZone.addCard(this);
        specialZone.applyCloseCombatWeather(this);
    }

    @Override
    public void apply(Card card, Row row) {
        if (card instanceof Unit && row instanceof CloseCombat) {
            ((Unit) card).setPoints(1);
        }
    }
}
