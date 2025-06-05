package edu.fiuba.algo3;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DeckBuilder {
    private List<Card> cards;
    private List<Card> selectedCards;
    private List<Card> units;
    private List<Card> specials;
    private List<DeckValidator> deckValidators;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.units = new ArrayList<>();
        this.specials = new ArrayList<>();
        this.deckValidators = Arrays.asList(
                new Validate6SpecialCards(specials),
                new Validate15UnitCards(units)
        );
        createCards();
    }

    private void createCards() {
        cards.add(new Decoy("Decoy", ""));
        cards.add(new Mardroeme("Mardroeme", ""));
        cards.add(new Mardroeme("Mardroeme", ""));
        cards.add(new Scorch("Scorch", ""));
        cards.add(new CommandersHorn("Commander's Horn", ""));
        cards.add(new BitingFrost("Biting Frost", ""));
        cards.add(new TorrentialRain("Torrential Rain", ""));
        cards.add(new TorrentialRain("Torrential Rain", ""));

        cards.add(new Unit("Botchling", "", 4, new CloseCombat()));
        cards.add(new Unit("Berserker", "", 4, new CloseCombat()));
        cards.add(new Unit("Dandelion", "", 2, new CloseCombat()));
        cards.add(new Unit("Dandelion", "", 2, new CloseCombat()));
        cards.add(new Unit("Griffin", "", 5, new CloseCombat()));
        cards.add(new Unit("Cerys", "", 10, new CloseCombat()));
        cards.add(new Unit("Cow", "", 0, new Ranged()));
        cards.add(new Unit("Dethmold", "", 6, new Ranged()));
        cards.add(new Unit("Harpy", "", 2, new Ranged()));
        cards.add(new Unit("Toad", "", 7, new Ranged()));
        cards.add(new Unit("Cynthia", "", 4, new Ranged()));
        cards.add(new Unit("Ermion", "", 8, new Ranged()));
        cards.add(new Unit("Ballista", "", 6, new Siege()));
        cards.add(new Unit("Catapult", "", 8, new Siege()));
        cards.add(new Unit("Thaler", "", 1, new Siege()));
        cards.add(new Unit("Ice Giant", "", 5, new Siege()));
    }

    public List<Card> getCards() {
        return cards;
    }

    public Deck buildDeck() {
        for (DeckValidator deckValidator : this.deckValidators) {
            if (!(deckValidator.validate())) {
                throw new IllegalArgumentException("Not Enough Cards");
            }
        }
        return new Deck(this.units, this.specials);
    }

    public void selectUnitCard(Card card) {
        units.add(card);
    }

    public void selectSpecialCard(Card card) {
        specials.add(card);
    }

    public void unselectUnitCard(Card card) {
        units.remove(card);
    }

    public void unselectSpecialCard(Card card) {
        specials.remove(card);
    }

    public void selectCard(Card card) {
        if (this.cards.remove(card)) {
            selectedCards.add(card);
        } //si no está, lanzar excepcion
        card.selectInDeckBuilder(this);
    }

    public void unselectCard(Card card) {
        selectedCards.remove(card);
        card.unselectInDeckBuilder(this);
    }

    public List<Card> getSelection() {
        return selectedCards;
    }

    public List<Card> getSpecialsSelected() {
        return specials;
    }

    public List<Card> getUnitsSelected() {
        return units;
    }
}