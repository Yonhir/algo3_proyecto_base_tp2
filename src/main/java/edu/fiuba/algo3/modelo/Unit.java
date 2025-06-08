package edu.fiuba.algo3.modelo;

import java.util.List;

public class Unit extends Card {
    private int basePoints;
    private int currentPoints;
    private List<Modifier> modifiers;
    private boolean puedeIrEnCloseCombat;
    private boolean puedeIrEnRanged;
    private boolean puedeIrEnSiege;

    public Unit(String name, String description, int points, boolean closeCombat, boolean ranged, boolean siege, List<Modifier> modifiers) {
        super(name, description);
        this.basePoints = points;
        this.currentPoints = points;
        this.puedeIrEnCloseCombat = closeCombat;
        this.puedeIrEnRanged = ranged;
        this.puedeIrEnSiege = siege;
        this.modifiers = modifiers;
    }

    public boolean puedeIrEnCloseCombat() {
        return puedeIrEnCloseCombat;
    }

    public boolean puedeIrEnRanged() {
        return puedeIrEnRanged;
    }

    public boolean puedeIrEnSiege() {
        return puedeIrEnSiege;
    }

    @Override
    public void play(Row row) {
        row.placeCard(this);
    }


    @Override
    public boolean puedeSerColocadaEn(Row row) {
        return row.puedeColocarUnidad(this);
    }

}
