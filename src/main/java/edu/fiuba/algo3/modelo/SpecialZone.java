package edu.fiuba.algo3.modelo;

import java.util.List;

public class SpecialZone {
    private List<Row> closeCombat;
    private List<Row> ranged;
    private List<Row> siege;

    public SpecialZone(List<Row> closeCombat, List<Row> ranged, List<Row> siege) {
        this.closeCombat = closeCombat;
        this.ranged = ranged;
        this.siege = siege;
    }

    public void placeCard(Card card) {

    }
}