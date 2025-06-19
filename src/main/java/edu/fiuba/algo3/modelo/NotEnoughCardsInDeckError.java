package edu.fiuba.algo3.modelo;

public class NotEnoughCardsInDeckError extends RuntimeException{
    public NotEnoughCardsInDeckError(String message){
        super(message);
    }
}
