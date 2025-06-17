package edu.fiuba.algo3.modelo;

import java.util.Arrays;
import java.util.List;

public class MoraleBoost extends Special {
    private Row[] rows;

    public MoraleBoost(String name, String description, Row... rows) {
        super(name, description);
        this.rows = rows;
    }

    @Override
    public void play(CardTarget target) {
        if (target instanceof Row){
            Row row = (Row) target;
            if(Arrays.stream(rows).noneMatch(row1 -> row1.equals(row))) throw new CardPlayInOpponentPlaceException("Morale Boost can only be played in your own rows.");
            List<Card> cards= row.getCards();
            for (Card card : cards) {
                int points = ((Unit) card).calculatePoints();
                ((Unit) card).setPoints(points * 2);
            }
        }
    }
}
