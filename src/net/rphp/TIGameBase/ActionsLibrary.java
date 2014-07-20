package net.rphp.TIGameBase;

import java.util.ArrayList;

public class ActionsLibrary
{
	// fields

	public ActionsLibrary()
	{
		// nothing to do here at the moment
	}


	public String executeCommand(String commandWord)
	{
		String response = "";

		switch(commandWord) {
			case "pregamehelp":
				response = preGameHelp();
				break;
		}

		return response;
	}

	public String executeCommand(String commandWord, String parameter) throws InvalidCommandSyntaxException
	{
		String response = "";

		try {
			switch(commandWord) {
				case "new":
					newGame(parameter);
					break;
			}
		}catch(InvalidCommandSyntaxException ICSE) {
			throw new InvalidCommandSyntaxException("The syntax for this command is invalid.", ICSE);
		}

		return response;
	}

	public String executeCommand(String commandWord, ArrayList<String> parameters) throws InvalidCommandSyntaxException
	{
		String response = "";

		try {
			switch(commandWord) {
				case "load":
					loadGame(parameters);
					break;
			}
		}catch(InvalidCommandSyntaxException ICSE) {
			throw new InvalidCommandSyntaxException("The syntax for this command is invalid.", ICSE);
		}

		return response;
	}

	private void newGame(String parameter) throws InvalidCommandSyntaxException
	{
		if(!parameter.equals("game")) {
			throw new InvalidCommandSyntaxException("");
		}
	}

	private void loadGame(ArrayList<String> parameters) throws InvalidCommandSyntaxException
	{
		if(!parameters.get(0).equals("game")) {
			throw new InvalidCommandSyntaxException("");
		}

		//if(!parameters.get(1).) // matches? validate against file name saves in saves/ directory
	}

	private String preGameHelp()
	{
		return "pregame help here.";
	}
}