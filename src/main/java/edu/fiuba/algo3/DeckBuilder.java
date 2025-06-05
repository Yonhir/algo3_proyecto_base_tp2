package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder {
    private List<Card> cards;
    private List<Card> selectedCards;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        fillCards();
    }

    private void fillCards() {
        for (int i = 0; i < 15; i++) {
            int basePoint = (int) (Math.random() * 10) + 1;
            cards.add(new Unit("Unit " + i, "Description " + i, basePoint, new CloseCombat(), new ArrayList<>()));
        }
        cards.add(new ClearWeather());
        cards.add(new ClearWeather());
        cards.add(new BitingFrost());
        cards.add(new BitingFrost());
        cards.add(new ImpenetrableFog());
        cards.add(new TorrentialRain());
    }

    public List<Card> getSelection() {
        return new ArrayList<>(selectedCards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void selectCard(Card card) {
        cards.remove(card);
        selectedCards.add(card);
    }

    public void unselectCard(Card card) {
        selectedCards.remove(card);
        cards.add(card);
    }

    public CardCollection buildDeck() {
        if (selectedCards.stream().filter(card -> card instanceof Unit).count() != 15) {
            throw new IllegalArgumentException("There must be 15 Units in the deck");
        }
        if (selectedCards.stream().filter(card -> card instanceof Special).count() != 6) {
            throw new IllegalArgumentException("There must be 6 Special in the deck");
        }
        return new Deck(selectedCards);
    }

    public void reset() {
        cards = new ArrayList<>();
        selectedCards = new ArrayList<>();
        fillCards();
    }
}
