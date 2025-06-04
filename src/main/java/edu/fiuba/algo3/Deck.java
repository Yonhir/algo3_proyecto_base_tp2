package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Deck extends CardCollection {
    private List<Card> units;
    private List<Card> specials;

    public Deck(List<Card> units, List<Card> specials) {
        super(new ArrayList<>());
        this.units = units;
        this.specials = specials;
    }

    public int getUnitsCount() {
        return this.units.size();
    }

    public int getSpecialsCount() {
        return this.specials.size();
    }

}