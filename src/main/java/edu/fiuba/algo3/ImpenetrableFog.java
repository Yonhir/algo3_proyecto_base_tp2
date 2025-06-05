package edu.fiuba.algo3;

public class ImpenetrableFog extends Weather {
    public ImpenetrableFog() {
        super("Impenetrable Fog", "Sets the strength of all Ranged Combat cards to 1 for both players.", new Ranged());
    }

    public void play(Player owner, Player opponent) {
        // Logic to set all Ranged Combat cards to 1 for both players will be implemented later
    }
}
