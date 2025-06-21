package edu.fiuba.algo3.modelo.errors;

public class NotEnoughUnitsCardsError extends RuntimeException {
    public NotEnoughUnitsCardsError(String message) {
        super(message);
    }
}