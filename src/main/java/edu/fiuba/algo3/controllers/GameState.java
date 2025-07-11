package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.models.turnManagement.Round;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;

import java.util.ArrayList;

public class GameState {
    private Round roundActual;
    private UISpecialZone specialZoneActual;
    private ArrayList<UIRow> rows;
    private static GameState instance;

    public GameState() {
        rows = new ArrayList<>();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void setRoundActual(Round roundActual) {
        this.roundActual = roundActual;
    }

    public void setRows(UIRow row) { rows.add(row); }

    public void setSpecialZoneActual(UISpecialZone specialZoneActual) { this.specialZoneActual = specialZoneActual; }

    public Round getRoundActual() { return roundActual; }

    public ArrayList<UIRow> getRows() { return rows; }

    public UISpecialZone getSpecialZoneActual() { return specialZoneActual; }
}
