package dev.rajat.User.service.Exception;

public class InvalidTokenException extends Exception{

    public InvalidTokenException(String message)
    {
        super(message);
    }
}
