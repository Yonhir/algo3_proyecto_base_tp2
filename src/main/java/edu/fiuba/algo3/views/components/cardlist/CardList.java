package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.Observer;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICardFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public abstract class CardList extends Pane implements Observer {
    private static final double DEFAULT_CARD_LAYOUT_Y = 0;
    
    private final List<UICard> cards;
    protected Observable model;

    public CardList() {
        super();
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        this.cards = new ArrayList<>();
        
        setupCardListStyling();
        
        widthProperty().addListener((observable, oldValue, newValue) -> updateOptimalPositioning());
    }
    
    private void setupCardListStyling() {
        javafx.scene.layout.VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
        javafx.scene.layout.HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        applyDefaultStyle();
    }

    protected void applyDefaultStyle() {
        setStyle("-fx-border-color: #8B4513; -fx-border-width: 2px; -fx-background-color: transparent;");
    }

    protected void applyHoverStyle() {
        setStyle("-fx-border-color: #FFD700; -fx-border-width: 2px; -fx-background-color: transparent;");
    }

    public void updateOptimalPositioning() {
        if (cards.isEmpty()) {
            return;
        }

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
            UICard.setLayoutY(DEFAULT_CARD_LAYOUT_Y);
        }
    }

    public void addCard(UICard UICard) {
        cards.add(UICard);
        getChildren().add(UICard);
        updateOptimalPositioning();
    }

    public List<UICard> getCards() {
        return cards;
    }

    protected void setModel(Observable model) {
        this.model = model;
        subscribeToModel();
        loadCardsFromModel();
    }

    protected void subscribeToModel() {
        if (model != null) {
            model.addObserver(this);
        }
    }
    
    protected void loadCardsFromModel() {
        getChildren().clear();
        cards.clear();
        
        List<Card> modelCards = getCardsFromModel();
        for (Card modelCard : modelCards) {
            UICard uiCard = createUICard(modelCard);
            addCard(uiCard);
        }
        updateOptimalPositioning();
    }
    
    protected UICard createUICard(Card modelCard) {
        return UICardFactory.createUICard(modelCard);
    }
    
    protected abstract List<Card> getCardsFromModel();
    
    protected boolean isModel(Observable observable) {
        return observable == model;
    }
    
    @Override
    public void update(Observable observable) {
        if (isModel(observable)) {
            loadCardsFromModel();
        }
    }

}
