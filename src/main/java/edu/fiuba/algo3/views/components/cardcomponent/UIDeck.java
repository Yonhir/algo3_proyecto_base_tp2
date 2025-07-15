package edu.fiuba.algo3.views.components.cardcomponent;

import edu.fiuba.algo3.models.Observable;
import edu.fiuba.algo3.models.cardcollections.Deck;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class UIDeck extends BaseCardComponent {
    private final Deck deck;
    private final Label cardCountLabel;


    public UIDeck(Deck deck) {
        super();
        this.deck = deck;
        this.deck.addObserver(this);
        this.cardCountLabel = new Label();
        cardCountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        cardCountLabel.setMouseTransparent(true);

        getChildren().add(cardCountLabel);
        updateCardCount();
    }

    @Override
    protected void initializeComponent() {
        setupBackground();

    }
    private void updateCardCount() {
        cardCountLabel.setText(String.valueOf(deck.getCardCount()));
    }

    @Override
    protected Color getFillColor() {
        return Color.rgb(139, 69, 19); // Saddle brown for deck back
    }

    @Override
    protected Color getStrokeColor() {
        return Color.rgb(101, 67, 33); // Darker brown border
    }

    @Override
    public void update(Observable observable) {
        updateCardCount();
    }
}
