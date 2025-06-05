package edu.fiuba.algo3;

public class TorrentialRain extends Weather {
    public TorrentialRain() {
        super("Torrential Rain", "Sets the strength of all Siege Combat cards to 1 for both players.", new Siege());
    }

    public void play(Player owner, Player opponent) {
        // Logic to set all Siege Combat cards to 1 for both players will be implemented later
    }
}
