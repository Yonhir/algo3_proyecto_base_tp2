package edu.fiuba.algo3.views.components.cardlist;

import edu.fiuba.algo3.controllers.GameState;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.sections.rows.Row;
import edu.fiuba.algo3.views.components.cardcomponent.card.UICard;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class UIRow extends CardList {
    private boolean encendidoAlMoverCarta;

    public UIRow() {
        super(false);
    }

    public UIRow(Row modelRow) {
        super(false);
        encendidoAlMoverCarta = false;
        setModel(modelRow);
        GameState.getInstance().setRows(this);
    }

    @Override
    protected List<Card> getCardsFromModel() {
        return ((Row) model).getCards();
    }

    public void encenderSpecial(){
        setStyle("-fx-border-color: #FFD700; -fx-border-width: 2px; -fx-background-color: transparent;");
        encendidoAlMoverCarta = true;
    }

    public void encender(Card card) {
        Row row = (Row) model;
        if(row.haveSameSectionType(card) && row.haveSamePlayerColor(card)) {
            setStyle("-fx-border-color: #FFD700; -fx-border-width: 2px; -fx-background-color: transparent;");
            encendidoAlMoverCarta = true;
        }
    }

    public void apagar() {
        if (encendidoAlMoverCarta) {
            setStyle("-fx-border-color: #8B4513; -fx-border-width: 2px; -fx-background-color: transparent;");
            encendidoAlMoverCarta = false;
        }
    }

    public void colocarCartaPorArrastre(UICard uiCard, MouseEvent event) {
        Row row = (Row) model;
        if (encendidoAlMoverCarta) {
            Bounds bounds = localToScene(getBoundsInLocal());
            if (bounds.contains(event.getSceneX(), event.getSceneY())) {
                row.placeCard(uiCard.getModel());
                addCard(uiCard);
                update(uiCard.getModel());
                update(model);
            }
        }
    }

    public void actualizarCartas(){
        update(model);
    }
}
