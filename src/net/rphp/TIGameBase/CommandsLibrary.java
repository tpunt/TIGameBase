package net.rphp.TIGameBase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class holds every available command in both pregame and in-game mode.
 * It is set as a Singleton because we only need one instance of this class
 * in-memory in order to validate the input command.
 */
public class CommandsLibrary
{
    private ArrayList<ArrayList<String>> commandWords;
    private ArrayList<String> preGameCommandWords;
    private static CommandsLibrary instance = null;
    private HashMap<String, String> commandWordsManual;

    /**
     * Create the array lists and hash maps of command words and their descriptions.
     * <p>
     * The commandWords array has a particular semantic meaning on the index where
     * the command words are inserted into the array list. Command words inserted at
     * index 0 have 0 arguments; command words inserted at index 1 have 1 argument,
     * index 2 command words have 2 arguments, and so on.
     * <p>
     * This constructor is deliberately set to have a private scope, in order to
     * enforce the Singleton design pattern. 
     */
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
    }

    /**
     * Either get the current instance of the CommandsLibrary class, or create
     * a new instance, store it for successive requests, and then return it.
     *
     * @return 	the only CommandsLibrary object in memory
     */
    public static CommandsLibrary getInstance()
    {
        if(instance == null)
            instance = new CommandsLibrary();

        return instance;
    }

    /**
     * Loops through every command word in the commandWords array list and
     * checks for the presence of the input command word.
     *
     * @return 	whether the input command word is valid
     */
    public boolean validateKeyword(String keyword)
    {
        for(ArrayList<String> commands : commandWords) {
            if(commands.contains(keyword))
                return true;
        }

        return false;
    }

    /**
     * Validates the argument count associated with the input command word
     * by looping through the array list where the index is equal to the
     * argument count in the commandWords variable.
     *
     * @return 	whether the basic command syntax (argument count) is valid
     */
    public boolean validateCommandSyntax(String keyword, int argumentCount)
    {
        if(commandWords.get(argumentCount).contains(keyword))
            return true;

        return false;
    }

    /**
     * Checks to see if the input command word is a pregame command.
     *
     * @return 	whether the input command word is a pregame command
     */
    public boolean isPreGameKeyword(String keyword)
    {
        if(preGameCommandWords.contains(keyword))
            return true;

        return false;
    }

    /**
     * Get all pregame command words.
     *
     * @return 	an array list of all pregame command words
     */
    public ArrayList<String> getPreGameCommands()
    {
        return preGameCommandWords;
    }

    /**
     * Get all in-game command words.
     *
     * @return an array list of all commands
     */
    public ArrayList<ArrayList<String>> getInGameCommands()
    {
        return commandWords;
    }

    /**
     * Get the description for a particular command word.
     *
     * @return 	the description for the specified command word
     */
    public String getManualForCommand(String commandName)
    {
        return commandWordsManual.get(commandName);
    }
}