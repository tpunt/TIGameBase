package net.rphp.TIGameBase;

public class InvalidCommandWordException extends Exception
{
	public InvalidCommandWordException(String errorMessage)
	{
		super(errorMessage);
	}

	public InvalidCommandWordException(String errorMessage, Throwable throwableObject)
	{
		super(errorMessage, throwableObject);
	}
}