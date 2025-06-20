package edu.fiuba.algo3.modelo.errors;

public class NotEnoughCardsInDeckError extends RuntimeException{
    public NotEnoughCardsInDeckError(String message){
        super(message);
    }
}
