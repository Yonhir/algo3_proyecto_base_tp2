package edu.fiuba.algo3.modelo.errors;

public class InvalidCardAmountError extends RuntimeException{
    public InvalidCardAmountError(String message){
        super(message);
    }
}
