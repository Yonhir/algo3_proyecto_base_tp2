package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck extends CardCollection {
    public Deck(List<Card> cards) {
        super(cards);
        List<DeckValidator> validators = Arrays.asList(
                new Validate6SpecialCards(),
                new Validate15UnitsCards()
        );
        validate(validators);
    }

    private void validate(List<DeckValidator> validators) {
        for (DeckValidator deckV : validators) {
            deckV.validate(cards);
        }
    }

    public int getCardCount() {
        return cards.size();
    }

    public long getUnitsCount() {
        return cards.stream().filter(card -> card instanceof Unit).count();
    }

    public long getSpecialsCount() {
        return cards.stream().filter(card -> card instanceof Special).count();
    }
  
    public List<Card> retrieveNRandomCards(int n){
        if(cards.isEmpty() || cards.size() < n){
            throw new NotEnoughtCardsInDeckError("Deck without enough cards");
        }
        Collections.shuffle(cards);
        List<Card> selectedCards = new ArrayList<>(cards.subList(0, n));
        cards.subList(0, n).clear();
        return selectedCards;
    }
}
