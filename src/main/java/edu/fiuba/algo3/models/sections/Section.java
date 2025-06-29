package edu.fiuba.algo3.models.sections;

import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.models.cards.Card;

public interface Section {
    void placeCard(Card card, Round round);
}
