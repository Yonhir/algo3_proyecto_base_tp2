package edu.fiuba.algo3.modelo;

public class InvalidCardAmountError extends RuntimeException{
    public InvalidCardAmountError(String message){
        super(message);
    }
}
