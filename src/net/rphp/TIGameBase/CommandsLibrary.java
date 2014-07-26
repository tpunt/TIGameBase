package net.rphp.TIGameBase;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandsLibrary
{
	private ArrayList<ArrayList<String>> commandWords;
	private ArrayList<String> preGameCommandWords;
	private static CommandsLibrary instance = null;
	private HashMap<String, String> commandWordsManual;

	private CommandsLibrary()
	{
		commandWords = new ArrayList<ArrayList<String>>();
		preGameCommandWords = new ArrayList<String>();
		commandWordsManual = new HashMap<String, String>();

		commandWordsManual.put("new", "Load a new game. Syntax: 'new game'.");
		commandWordsManual.put("load", "Load a saved game. Syntax: 'load game GAME_NAME'.");
		commandWordsManual.put("quit", "Quit the in-game or pregame mode. Syntax: 'quit'.");
		commandWordsManual.put("help", "Show help. Syntax: 'help'.");
		commandWordsManual.put("manual", "Either show all valid commands or view information about a specific command. Syntax: 'manual' or 'manual COMMAND_NAME'.");
		commandWordsManual.put("in", "Test function. Syntax: 'in game command'.");

		preGameCommandWords.add("new");
		preGameCommandWords.add("load");
		preGameCommandWords.add("quit");
		preGameCommandWords.add("help");
		preGameCommandWords.add("manual");

		commandWords.add(new ArrayList<String>());
		commandWords.get(0).add("quit");
		commandWords.get(0).add("help");
		commandWords.get(0).add("manual");

		commandWords.add(new ArrayList<String>());
		commandWords.get(1).add("new");
		commandWords.get(1).add("manual");

		commandWords.add(new ArrayList<String>());
		commandWords.get(2).add("load");
		commandWords.get(2).add("in");
	}

	public static CommandsLibrary getInstance()
	{
		if(instance == null)
			instance = new CommandsLibrary();

		return instance;
	}

	public boolean validateKeyword(String keyword)
	{
		for(ArrayList<String> commands : commandWords) {
			if(commands.contains(keyword))
				return true;
		}

		return false;
	}

	public boolean validateCommandSyntax(String keyword, int argumentCount)
	{
		if(commandWords.get(argumentCount).contains(keyword))
			return true;

		return false;
	}

	public boolean isPreGameKeyword(String keyword)
	{
		if(preGameCommandWords.contains(keyword))
			return true;

		return false;
	}

	public ArrayList<String> getPreGameCommands()
	{
		return preGameCommandWords;
	}

	public ArrayList<ArrayList<String>> getInGameCommands()
	{
		return commandWords;
	}

	public String getManualForCommand(String commandName)
	{
		return commandWordsManual.get(commandName);
	}
}