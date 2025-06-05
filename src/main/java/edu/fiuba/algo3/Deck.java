package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends CardCollection {

    public Deck(List<Card> units, List<Card> specials) {
        super(units, specials);
    }

    public List<Card> getNRandomCard(int n) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(units);
        cards.addAll(specials);
        Collections.shuffle(cards);
        return cards.subList(0, n);
    }

    public int getCardCount() {
        return this.units.size() + this.specials.size();
    }

}