package edu.fiuba.algo3.modelo.colors;

public class Red extends PlayerColor {
    @Override
    public PlayerColor swapColor(){
        return new Blue();
    }
}
