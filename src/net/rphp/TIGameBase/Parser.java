package net.rphp.TIGameBase;

import java.io.InputStreamReader; //takes input in raw byte form
import java.io.BufferedReader; // reads one line at a time and translates the raw byte form into characters
import java.io.IOException; // required for the readLine() method in the BufferedReader class
import java.util.ArrayList;

/**
 * This class is used to get the user input, parse it, and return the
 * resultant action via a ResultantAction object.
 */
public class Parser
{
    private CommandsLibrary commandsLibrary;
    private ActionsLibrary actionsLibrary = new ActionsLibrary();

    public Parser()
    {
        commandsLibrary = CommandsLibrary.getInstance();
    }

    /**
     * Take in the user input and return it in lowercase.
     *
     * @return 	the user input in lowercase
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

    /**
     * Perform basic validate upon the input command. If the command is valid, execute
     * its corresponding method from ActionsLibrary.
     *
     * @return	A ResultantAction object from either a command method invocation, exception thrown, or validation error.
     */
    public ResultantAction parseCommand(String command, boolean isPreGame)
    {
        ArrayList<String> tokens = new ArrayList<String>();
        String commandWord;

        if(command.equals(""))
            return new ResultantAction("", "Empty commands are invalid.", true);

        for(String token : command.split("\\s+"))
            tokens.add(token);

        if(tokens.size() == 0)
            return new ResultantAction("", "Empty commands are invalid.", true);

        commandWord = tokens.get(0);
        tokens.remove(0);

        if(!commandsLibrary.validateKeyword(commandWord))
            return new ResultantAction(commandWord, "The command word is invalid.", true);

        if(isPreGame) {
            if(!commandsLibrary.isPreGameKeyword(commandWord))
                return new ResultantAction(commandWord, "The command word is not a pre-game command.", true);
        }else{
            // check arg to manual command
        }

        if(!commandsLibrary.validateCommandSyntax(commandWord, tokens.size()))
            return new ResultantAction(commandWord, "The command syntax is invalid (incorrect argument count).", true);

        if(isPreGame) {
            if(commandWord.equals("help"))
                commandWord = "pregamehelp";

            if(commandWord.equals("manual"))
                commandWord = "pregamemanual";
        }

        try {
            switch(tokens.size()) {
                case 0:
                    return new ResultantAction(commandWord, actionsLibrary.executeCommand(commandWord));
                case 1:
                    return new ResultantAction(commandWord, actionsLibrary.executeCommand(commandWord, (String) tokens.get(0)));
                default:
                    return new ResultantAction(commandWord, actionsLibrary.executeCommand(commandWord, tokens));
            }
        }catch(InvalidCommandSyntaxException ICSE) {
            return new ResultantAction(commandWord, "The command syntax is invalid (incorrect use of command).", true);
        }
        // put the following in a try...catch block, since command word methods will throw exceptions when something occurs
        // perform commandWord method invocation and get the response ?
        //return new ResultantAction(commandWord, commandsLibrary.methodInvocation());
        //return new ResultantAction(commandWord, "Success...");
    }
}