package dev.rajat.User.service.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message)
    {
    super(message);

    }
}
