package edu.fiuba.algo3.models.errors;

public class NotEnoughCardsInDeckError extends RuntimeException{
    public NotEnoughCardsInDeckError(String message){
        super(message);
    }
}
