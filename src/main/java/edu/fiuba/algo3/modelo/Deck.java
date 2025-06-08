package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck extends CardCollection {

    public Deck(List<Card> cards) {
        super(cards);
    }

    public List<Card> retrieveNRandomCards(int n){
        Collections.shuffle(cards);
        return cards.subList(0,n);
    }
}
