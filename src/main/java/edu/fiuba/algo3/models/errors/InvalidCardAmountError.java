package edu.fiuba.algo3.models.errors;

public class InvalidCardAmountError extends RuntimeException{
    public InvalidCardAmountError(String message){
        super(message);
    }
}
