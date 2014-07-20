package net.rphp.TIGameBase;

public class InvalidCommandSyntaxException extends Exception
{
	public InvalidCommandSyntaxException(String errorMessage)
	{
		super(errorMessage);
	}

	public InvalidCommandSyntaxException(String errorMessage, Throwable throwableObject)
	{
		super(errorMessage, throwableObject);
	}
}