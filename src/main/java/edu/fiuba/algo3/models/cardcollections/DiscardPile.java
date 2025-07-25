package edu.fiuba.algo3.models.cardcollections;

import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.colors.PlayerColor;
import edu.fiuba.algo3.models.errors.NotUnitCardError;
import edu.fiuba.algo3.models.sections.rows.Row;

import java.util.List;

public class DiscardPile extends CardCollection {
    private PlayerColor color;

    @Override
    public void addCard(Card card) {
        if (card instanceof Unit) {
            ((Unit) card).resetPoints();
        }
        super.addCard(card);
    }

    @Override
    public void insertCards(List<Card> cards) {
        for (Card c : cards) {
            addCard(c);
        }
    }

    public Card getLastCard(){
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot get last card from empty discard pile");
        }
        int lastCard = (cards.size() - 1);
        return cards.get(lastCard);
    }
    public Card deleteLastCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot get last card from empty discard pile");
        }
        int lastCard = (cards.size() - 1);
        return cards.remove(lastCard);
    }

    public void setColor(PlayerColor color){
        this.color = color;
    }

    public void addCardIfHasSameColor(Card card){
         if (card.haveSameColor(color)){
             addCard(card);
         }
    }

    public Card getLastUnitCardFromType(Row row){
        for (int i = cards.size() - 1; i >= 0; i--) {
            Card card = cards.get(i);
            if (card instanceof Unit && row.haveSameSectionType(card)) {
                cards.remove(i);
                return card;
            }
        }
       throw new NotUnitCardError("No unit card found in discard pile");
    }
}
