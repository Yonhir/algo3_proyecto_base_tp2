package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Row {
    protected List<Card> cards = new ArrayList<>();

    public void placeCard(Card card) {
        if (!card.puedeSerColocadaEn(this)) {
            throw new IllegalArgumentException("La carta no puede colocarse en esta fila.");
        }
        card.play(this);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean puedeColocarUnidad(Unit unit) {
        return false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

}
