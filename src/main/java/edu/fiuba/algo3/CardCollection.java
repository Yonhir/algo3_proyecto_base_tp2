package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public abstract class CardCollection {
    protected List<Card> units;
    protected List<Card> specials;

    public CardCollection(List<Card> units, List<Card> specials) {
        this.units = units;
        this.specials = specials;
    }

    public abstract int getCardCount();
}