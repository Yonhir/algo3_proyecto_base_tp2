package edu.fiuba.algo3;

import java.util.List;

public class Hand extends CardCollection{


    public Hand(List<Card> units, List<Card> specials) {
        super(units, specials);
    }

    public void selectUnitCard(Card card){
        units.add(card);
    }
    public void selectSpecialCard(Card card){
        specials.add(card);
    }
    @Override
    public int getCardCount() {
        return this.units.size() + this.specials.size();
    }
}
