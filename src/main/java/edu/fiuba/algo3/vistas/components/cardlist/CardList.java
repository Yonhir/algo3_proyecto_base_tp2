package edu.fiuba.algo3.vistas.components.cardlist;

import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.vistas.components.cardcomponent.card.UICard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public abstract class CardList extends Pane implements Observer {
    
    private final List<UICard> cards;
    private final boolean defaultDraggable;

    public CardList() {
        this(false);
    }
    
    public CardList(boolean defaultDraggable) {
        super();
        this.defaultDraggable = defaultDraggable;
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        this.cards = new ArrayList<>();
        
        setupCardListStyling();
        setupHoverAnimation();
        
        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                updateOptimalPositioning();
            }
        });
    }
    
    private void setupCardListStyling() {
        javafx.scene.layout.VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
        javafx.scene.layout.HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        applyDefaultStyle();
    }
    
    private void setupHoverAnimation() {
        setOnMouseEntered(e -> applyHoverStyle());
        setOnMouseExited(e -> applyDefaultStyle());
    }
    
    private void applyDefaultStyle() {
        setStyle("-fx-border-color: #8B4513; -fx-border-width: 2px; -fx-background-color: transparent;");
    }
    
    private void applyHoverStyle() {
        setStyle("-fx-border-color: #FFD700; -fx-border-width: 2px; -fx-background-color: transparent;");
    }

    public void updateOptimalPositioning() {
        if (cards.isEmpty()) {
            return;
        }
        double cardWidth;

        double width = getWidth();
        UICard aCard = cards.get(0);
        double widthWithoutACard = width - aCard.getWidth();
        double padding;
        if (cards.size() > 1) {
            padding = widthWithoutACard / cards.size();
            padding = Math.round(padding);
        } else {
            padding = 0;
        }
        for (int i = 0; i < cards.size(); i++) {
            UICard UICard = cards.get(i);
            double xPosition = padding * i;
            UICard.setLayoutX(xPosition);
            UICard.setLayoutY(0);
        }
    }

    public void addCard(UICard UICard) {
        setCardDraggable(UICard);
        cards.add(UICard);
        getChildren().add(UICard);
        updateOptimalPositioning();
    }

    protected void setCardDraggable(UICard UICard) {
        UICard.setDraggable(defaultDraggable);
    }

    public void addCards(UICard... cardsToAdd) {
        for (UICard UICard : cardsToAdd) {
            addCard(UICard);
        }
    }

    public List<UICard> getCards() {
        return new ArrayList<>(cards);
    }
}
