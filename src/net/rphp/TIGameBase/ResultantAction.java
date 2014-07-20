package net.rphp.TIGameBase;

// imports

public class ResultantAction
{
	private String commandWord;
	private boolean errorOccurred = false;
	private String responseMessage;

	public ResultantAction(String commandWord, String responseMessage, boolean errorOccurred)
	{
		this.commandWord = commandWord;
		this.responseMessage = responseMessage;
		this.errorOccurred = errorOccurred;
	}

	public ResultantAction(String commandWord, String responseMessage)
	{
		this.commandWord = commandWord;
		this.responseMessage = responseMessage;
	}

	public String getCommandWord()
	{
		return commandWord;
	}

	public boolean hasError()
	{
		return errorOccurred;
	}

	public String getResponse()
	{
		return responseMessage;
	}
}