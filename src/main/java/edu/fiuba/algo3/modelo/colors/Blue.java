package edu.fiuba.algo3.modelo.colors;

public class Blue extends PlayerColor {
    @Override
    public Red swapColor(){
        return new Red();
    }
}
