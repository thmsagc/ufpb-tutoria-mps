package br.ufpb.tutoria.exception;

public class ExistingUserException extends Exception{
    public ExistingUserException(String message) {
        super(message);
    }
}