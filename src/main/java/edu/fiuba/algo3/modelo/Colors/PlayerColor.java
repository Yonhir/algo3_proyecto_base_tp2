package edu.fiuba.algo3.modelo.Colors;

public abstract class PlayerColor {
    public abstract PlayerColor swapColor();

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
