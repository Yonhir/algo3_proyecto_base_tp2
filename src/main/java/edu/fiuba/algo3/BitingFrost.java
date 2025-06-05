package edu.fiuba.algo3;

public class BitingFrost extends Weather {
    public BitingFrost() {
        super("Biting Frost", "Sets the strength of all Close Combat cards to 1 for both players.", new CloseCombat());
    }
}
