package net.rphp.TIGameBase;

import java.util.ArrayList;

/**
 * This class contains the methods that will be invoked depending upon the
 * input command. It will validate multi-word commands and pass invalid syntax
 * exceptions up the invocation chain to be handled by
 */
public class ActionsLibrary
{
    private CommandsLibrary commandsLibrary;

    /**
     * Get an instance of the CommandsLibrary class
     */
    public ActionsLibrary()
    {
        commandsLibrary = CommandsLibrary.getInstance();
    }

    /**
     * Executes the corresponding method to the command word and return the response.
     *
     * @return	the response from the method of the command word.
     */
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

    /**
     * Passes the command word argument to the corresponding command word method
     * and returns the response. If an exception is thrown from one of the executed
     * methods, it will bubble up the execution chain to be handled by the ActionsLibrary
     * calling code.
     *
     * @return 	the response from the method of the command word.
     */
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

    /**
     * Passes the command word arguments to the corresponding command word method
     * and returns the response. If an exception is thrown from one of the executed
     * methods, it will bubble up the execution chain to be handled by the ActionsLibrary
     * calling code.
     *
     * @return 	the response from the method of the command word.
     */
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

    /**
     * validate the 'new game' command to ensure it contains no syntax errors.
     */
    private void newGame(String parameter) throws InvalidCommandSyntaxException
    {
        if(!parameter.equals("game")) {
            throw new InvalidCommandSyntaxException("");
        }
    }

    /**
     * validate the 'load game' command to ensure it contains no syntax errors.
     */
    private void loadGame(ArrayList<String> parameters) throws InvalidCommandSyntaxException
    {
        if(!parameters.get(0).equals("game")) {
            throw new InvalidCommandSyntaxException("");
        }

        //if(!parameters.get(1).) // matches? validate against file name saves in saves/ directory
    }

    /**
     * Generates a message when the 'help' command is used in pregame mode.
     *
     * @return 	a string providing help in pregame mode
     */
    private String preGameHelp()
    {
        return "Type 'manual' to view the pregame commands available\n"
               + "And use the 'manual command_word_here' command to view details of a specific command";
    }

    /**
     * Generates a message when 'manual' is typed in pregame mode containing all
     * commands available to pregame mode
     *
     * @return 	a string of all pregame commands available
     */
    private String preGameManual()
    {
        String commands = "Pregame commands:";

        for(String command : commandsLibrary.getPreGameCommands())
            commands += "\n - " + command;

        return commands;
    }

    /**
     * Searches and returns the string description of the command specified.
     *
     * @return 	the command description
     */
    private String preGameManual(String command)
    {
        return commandsLibrary.getManualForCommand(command);
    }

    /**
     * Searches and returns the string description of the command specified.
     *
     * @return 	the command description
     */
    private String manual(String command)
    {
        return preGameManual(command);
    }

    /**
     * Generates a string containing all in-game commands, including their argument count.
     *
     * @return 	a string containing every in-game command
     */
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