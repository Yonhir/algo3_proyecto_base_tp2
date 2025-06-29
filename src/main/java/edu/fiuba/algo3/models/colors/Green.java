package edu.fiuba.algo3.models.colors;

public class Green extends PlayerColor {
    @Override
    public PlayerColor swapColor(){
        return new Green();
    }
}
