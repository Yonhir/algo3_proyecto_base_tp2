package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICardFactory;
import javafx.scene.paint.Color;

public class UIDiscardPile extends BaseCardComponent {
    private final DiscardPile discardPile;
    private UICard lastDiscardedUICard;
    public UIDiscardPile(DiscardPile discardPile) {
        super();
        this.discardPile = discardPile;
        this.discardPile.addObserver(this);
        update(discardPile);
    }

    @Override
    protected void initializeComponent() {
        setupBackground();
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(169, 169, 169);
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(105, 105, 105);
    }

    @Override
    public void update(Observable observable) {

        if (!discardPile.isEmpty()) {
            Card lastCard = discardPile.getLastCard();
            lastDiscardedUICard = UICardFactory.createUICard(lastCard);
            lastDiscardedUICard.setMouseTransparent(true);
            getChildren().add(lastDiscardedUICard);
        }
        // Update UI when discard pile changes (e.g., cards are added)
        // Could update card count display or visual representation
    }
}