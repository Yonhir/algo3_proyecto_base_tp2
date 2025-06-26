package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class CardList extends Pane {
    
    private final List<Card> cards;

    public CardList() {
        super();
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        this.cards = new ArrayList<>();
        
        // Add listener to recalculate positioning when width changes
        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                calculateOptimalPositioning();
            }
        });
    }

    public void calculateOptimalPositioning() {
        double cardWidth;
        if (cards.isEmpty()) {
            return;
        }
        
        // 1. Get the width of the CardList
        double width = getWidth();
        
        // 2. Calculate the width of a card (all cards have the same dimensions)
        cardWidth = Card.BASE_CARD_WIDTH * (width / (1920 * 0.5));
        
        // 4. Calculate width without cards
        double widthWithoutACard = width - cardWidth;
        
        // 5. Calculate padding by dividing the remaining width by number of cards.
        double padding;
        if (cards.size() > 1) {
            padding = widthWithoutACard / cards.size();
            padding = Math.round(padding); // Round to nearest pixel
        } else {
            padding = 0; // Only one card, no padding needed
        }
        
        // 6. Position each card using the formula: padding * N
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            double xPosition = padding * i;
            card.setLayoutX(xPosition);
            card.setLayoutY(0); // Align to top
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        getChildren().add(card);
        calculateOptimalPositioning();
    }

    public void addCards(Card... cardsToAdd) {
        for (Card card : cardsToAdd) {
            addCard(card);
        }
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
