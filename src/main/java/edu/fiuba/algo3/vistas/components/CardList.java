package edu.fiuba.algo3.vistas.components;

import edu.fiuba.algo3.modelo.Observer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public abstract class CardList extends Pane implements Observer {
    
    private final List<Card> cards;
    private final boolean defaultDraggable; // Default draggable setting for cards

    public CardList() {
        this(false); // Default to non-draggable
    }
    
    public CardList(boolean defaultDraggable) {
        super();
        this.defaultDraggable = defaultDraggable;
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        this.cards = new ArrayList<>();
        
        // Setup styling and animations
        setupCardListStyling();
        setupHoverAnimation();
        
        // Add listener to recalculate positioning when width changes
        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                calculateOptimalPositioning();
            }
        });
    }
    
    private void setupCardListStyling() {
        // Set VBox and HBox grow priority to ALWAYS
        javafx.scene.layout.VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
        javafx.scene.layout.HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        
        // Set default style
        applyDefaultStyle();
    }
    
    private void setupHoverAnimation() {
        // Mouse enter event - change border to gold/yellow
        setOnMouseEntered(e -> applyHoverStyle());
        
        // Mouse exit event - return to brown border
        setOnMouseExited(e -> applyDefaultStyle());
    }
    
    private void applyDefaultStyle() {
        setStyle("-fx-border-color: #8B4513; -fx-border-width: 2px; -fx-background-color: transparent;");
    }
    
    private void applyHoverStyle() {
        setStyle("-fx-border-color: #FFD700; -fx-border-width: 2px; -fx-background-color: transparent;");
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
        setCardDraggable(card);
        
        cards.add(card);
        getChildren().add(card);
        calculateOptimalPositioning();
    }

    protected void setCardDraggable(Card card) {
        card.setDraggable(defaultDraggable);
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
