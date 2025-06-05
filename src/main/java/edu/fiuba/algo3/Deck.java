package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Deck extends CardCollection {

    public Deck(List<Card> units, List<Card> specials) {
        super(units, specials);
    }

    public int getCardCount() {
        return this.units.size() + this.specials.size();
    }

    public int getUnitsCount() {
        return this.units.size();
    }

    public int getSpecialsCount() {
        return this.specials.size();
    }

}