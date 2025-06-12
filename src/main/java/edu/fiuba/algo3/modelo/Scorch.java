package edu.fiuba.algo3.modelo;

public class Scorch extends Card {
    public Scorch(String name, String description) {
       super(name, description);
    }

    @Override
    public void play(CardTarget target) {
        target.addCard(this);
    }

    public void addToSpecialZone(SpecialZone specialZone) {
        specialZone.addToAllRows(this);
    }
}