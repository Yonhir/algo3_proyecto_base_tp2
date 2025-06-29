package edu.fiuba.algo3.models.colors;

public class Blue extends PlayerColor {
    @Override
    public PlayerColor swapColor(){
        return new Red();
    }
}
