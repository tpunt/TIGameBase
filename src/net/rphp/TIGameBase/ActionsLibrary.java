package net.rphp.TIGameBase;

import java.util.ArrayList;

public class ActionsLibrary
{
	private CommandsLibrary commandsLibrary;

	public ActionsLibrary()
	{
		commandsLibrary = CommandsLibrary.getInstance();
	}


	public String executeCommand(String commandWord)
	{
		String response = "";

		switch(commandWord) {
			case "pregamehelp":
				response = preGameHelp();
				break;
			case "pregamemanual":
				response = preGameManual();
				break;
			case "manual":
				response = manual();
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
				case "pregamemanual":
					response = preGameManual(parameter);
					break;
				case "manual":
					response = manual();
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
				case "in":
					response = in(parameters);
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
		return "Type 'manual' to view the pregame commands available\n"
			   + "And use the 'manual command_word_here' command to view details of a specific command";
	}

	private String preGameManual()
	{
		String commands = "Pregame commands:";

		for(String command : commandsLibrary.getPreGameCommands())
			commands += "\n - " + command;

		return commands;
	}

	private String preGameManual(String command)
	{
		return commandsLibrary.getManualForCommand(command);
	}

	private String manual(String command)
	{
		return preGameManual(command);
	}

	private String manual()
	{
		String commandsMan = "All commands:";
		int commandArgCount = 0;

		for(ArrayList<String> commands : commandsLibrary.getInGameCommands()) {
			commandsMan +=  "\n" + commandArgCount + " argument commands";

			for(String command : commands) {
				commandsMan += "\n - " + command;
			}
			++commandArgCount;
		}

		return commandsMan;
	}

	private String in(ArrayList<String> arguments) throws InvalidCommandSyntaxException
	{
		if(!arguments.get(0).equals("game"))
			throw new InvalidCommandSyntaxException("");
		
		if(!arguments.get(1).equals("command"))
			throw new InvalidCommandSyntaxException("");

		return "in game command test";
	}
}