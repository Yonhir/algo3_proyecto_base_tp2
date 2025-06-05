package edu.fiuba.algo3;


public abstract class Special extends Card {
    public Special(String name, String description) {
        super(name, description);
    }
    public void selectInHand(Hand hand) {
        hand.selectSpecialCard(this);
    }
}
