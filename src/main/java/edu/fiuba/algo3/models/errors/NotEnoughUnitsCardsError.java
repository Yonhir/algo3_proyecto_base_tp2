package edu.fiuba.algo3.models.errors;

public class NotEnoughUnitsCardsError extends RuntimeException {
    public NotEnoughUnitsCardsError(String message) {
        super(message);
    }
}