package net.rphp.TIGameBase;

public class InvalidArgumentException extends Exception
{
    public InvalidArgumentException(String errorMessage)
    {
        super(errorMessage);
    }

    public InvalidArgumentException(String errorMessage, Throwable throwableObject)
    {
        super(errorMessage, throwableObject);
    }
}