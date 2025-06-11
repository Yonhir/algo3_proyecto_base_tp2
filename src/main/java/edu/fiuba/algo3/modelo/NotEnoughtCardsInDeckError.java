package edu.fiuba.algo3.modelo;

public class NotEnoughtCardsInDeckError extends RuntimeException{
    public NotEnoughtCardsInDeckError(String message){
        super(message);
    }
}
