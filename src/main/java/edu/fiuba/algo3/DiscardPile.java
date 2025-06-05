package edu.fiuba.algo3;

import java.util.List;

public class DiscardPile extends CardCollection{


    public DiscardPile(List<Card> units, List<Card> specials) {
        super(units, specials);
    }

    @Override
    public int getCardCount() {
        return this.units.size() + this.specials.size();
    }
}
