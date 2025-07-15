package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.errors.InvalidCardAmountError;
import edu.fiuba.algo3.models.errors.NotEnoughCardsInDeckError;
import edu.fiuba.algo3.models.turnManagement.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck extends CardCollection {
    private PlayerColor playerColor;

    public void validate() {
        List<DeckValidator> validators = Arrays.asList(
                new Validate15UnitsCards(),
                new Validate6SpecialCards()
        );
        for (DeckValidator deckV : validators) {
            deckV.validate(this.cards);
        }
        setColorToCards(playerColor);
    }

    public long getUnitsCount() {
        return cards.stream().filter(card -> card instanceof Unit).count();
    }

    public long getSpecialsCount() {
        return cards.stream().filter(card -> card instanceof Special).count();
    }

    public List<Card> retrieveNRandomCards(int n){
        if(cards.size() < n){
            throw new NotEnoughCardsInDeckError("Deck without enough cards");
        }
        if(n <= 0){
            throw new InvalidCardAmountError("Invalid number of cards requested, must be greater than zero");
        }
        Collections.shuffle(cards);
        List<Card> selectedCards = new ArrayList<>(cards.subList(0, n));
        cards.subList(0, n).clear();
        return selectedCards;
    }

    public void setColorToCards(PlayerColor playerColor) {
        for (Card card : cards) {
            card.setColor(playerColor);
        }
        this.playerColor = playerColor;
    }

    public void insertCardsInOrder(List<Card> cardsToInsert) {
        cards.addAll(0, cardsToInsert);
    }

    public List<Card> retrieveNTopCards(int n) {
        if (cards.size() < n) {
            throw new NotEnoughCardsInDeckError("Deck without enough cards");
        }
        List<Card> selectedCards = new ArrayList<>(cards.subList(0, n));
        cards.subList(0, n).clear();
        return selectedCards;
    }
}