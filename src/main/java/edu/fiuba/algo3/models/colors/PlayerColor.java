package edu.fiuba.algo3.models.colors;

public abstract class PlayerColor {
    public abstract PlayerColor swapColor();

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
