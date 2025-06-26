package edu.fiuba.algo3.modelo.colors;

public class Green extends PlayerColor {
    @Override
    public PlayerColor swapColor(){
        return new Green();
    }
}
