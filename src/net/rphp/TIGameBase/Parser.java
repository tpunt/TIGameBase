package net.rphp.TIGameBase;

import java.io.InputStreamReader; //takes input in raw byte form
import java.io.BufferedReader; // reads one line at a time and translates the raw byte form into characters
import java.io.IOException; // required for the readLine() method in the BufferedReader class
import java.util.ArrayList;

public class Parser
{
	private CommandsLibrary commandsLibrary;
	private ActionsLibrary actionsLibrary = new ActionsLibrary();

	public Parser()
	{
		commandsLibrary = commandsLibrary.getInstance();
	}

	/*
	 * Why is the Parser class getting the user input?
	 */
	public String getUserInput() throws IOException
	{
		String inputLine = "";
		BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));

		try {
    		inputLine = lineOfText.readLine();
		}catch(IOException IOE) {
			throw new IOException("Could not get user input.", IOE);
		}

		return inputLine.toLowerCase();
	}

	public ResultantAction parseCommand(String command, boolean isPreGame)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		String commandWord;

		if(command.equals(""))
			return new ResultantAction("", "Empty commands are invalid.", true);

		for(String token : command.split("\\s+"))
			tokens.add(token);

		if(tokens.size() == 0) {
			return new ResultantAction("", "Empty commands are invalid.", true);
		}

		commandWord = tokens.get(0);

		if(!commandsLibrary.validateKeyword(commandWord))
			return new ResultantAction(commandWord, "The command word is invalid.", true);

		if(isPreGame) {
			if(!commandsLibrary.isPreGameKeyword(commandWord))
				return new ResultantAction(commandWord, "The command word is not a pre-game command.", true);
		}

		if(!commandsLibrary.validateCommandSyntax(commandWord, tokens.size()-1))
			return new ResultantAction(commandWord, "The command syntax is invalid.", true);

		tokens.remove(0);

		if(isPreGame) {
			if(commandWord.equals("help"))
				commandWord = "pregamehelp";
		}

		try {
			switch(tokens.size()) {
				case 0:
					return new ResultantAction(commandWord, actionsLibrary.executeCommand(commandWord));
				case 1:
					String response = actionsLibrary.executeCommand(commandWord, (String) tokens.get(0));
					return new ResultantAction(commandWord, response);
				default:
					return new ResultantAction(commandWord, actionsLibrary.executeCommand(commandWord, tokens));
			}
		}catch(InvalidCommandSyntaxException ICSE) {
			return new ResultantAction(commandWord, "The syntax for command '" + commandWord + "' is invalid.", true);
		}
		// put the following in a try...catch block, since command word methods will throw exceptions when something occurs
		// perform commandWord method invocation and get the response ?
		//return new ResultantAction(commandWord, commandsLibrary.methodInvocation());
		//return new ResultantAction(commandWord, "Success...");
	}
}