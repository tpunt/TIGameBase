package net.rphp.TIGameBase;

import java.util.ArrayList;

public class CommandsLibrary
{
	private ArrayList<ArrayList<String>> commandWords;
	private ArrayList<String> preGameCommandWords;
	private static CommandsLibrary instance = null;

	private CommandsLibrary()
	{
		commandWords = new ArrayList<ArrayList<String>>();
		preGameCommandWords = new ArrayList<String>();

		preGameCommandWords.add("new");
		preGameCommandWords.add("load");
		preGameCommandWords.add("quit");
		preGameCommandWords.add("help");

		commandWords.add(new ArrayList<String>());
		commandWords.get(0).add("quit");
		commandWords.get(0).add("help");

		commandWords.add(new ArrayList<String>());
		commandWords.get(1).add("new");

		commandWords.add(new ArrayList<String>());
		commandWords.get(2).add("load");
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
}