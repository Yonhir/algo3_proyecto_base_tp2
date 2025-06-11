package edu.fiuba.algo3.modelo;

import java.util.List;

public class SpecialZone implements CardTarget {
    private List<Row> closeCombat;
    private List<Row> ranged;
    private List<Row> siege;

    public SpecialZone(List<Row> closeCombat, List<Row> ranged, List<Row> siege) {
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
    }

    public void placeCard(Card card) {
        card.play(this);
    }

    public void addCard(Card card) {
        card.addToSpecialZone(this);
    }

    public void addToAllRows(Card card) {
        int mostPowerfulCC = 0;
        int mostPowerfulR = 0;
        int mostPowerfulS = 0;

        for (Row closeCombat: this.closeCombat) {
            mostPowerfulCC = closeCombat.mostPowerfulCard();
        }

        for (Row ranged: this.ranged) {
            mostPowerfulR = ranged.mostPowerfulCard();
        }

        for (Row siege: this.siege) {
            mostPowerfulS = siege.mostPowerfulCard();
        }

        int mostPowerful = Math.max(mostPowerfulCC, Math.max(mostPowerfulR, mostPowerfulS));

        for (Row closeCombat: this.closeCombat) {
            closeCombat.deleteCardsWithPoints(mostPowerful);
        }

        for (Row ranged: this.ranged) {
            ranged.deleteCardsWithPoints(mostPowerful);
        }

        for (Row siege: this.siege) {
            siege.deleteCardsWithPoints(mostPowerful);
        }
    }
}