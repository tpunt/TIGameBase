package net.rphp.TIGameBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the opening class to the game base. It loads the Parser to take the
 * input commands and uses the options loaded from the settings.ini file to change
 * the game base's behaviour.
 */
public class GameBase
{
    private Parser parser = new Parser();
    private boolean isPreGame = true;
    private boolean inGame = false;
    private Settings settings;

    public static void main(String[] args)
    {
        GameBase gameBase = new GameBase();
    }

    /**
     * Load the Settings object and invoke the preGameMode method.
     */
    public GameBase()
    {
        settings = Settings.loadSettings("net/rphp/TIGameBase/settings.ini");

        System.out.println("Welcome!\r\n");

        preGameMode();
    }

    /**
     * Enter the pregame mode, or skip to in-game mode if the `pregame` setting
     * is set to 'false'.
     */
    private void preGameMode()
    {
        String userInputCommand;
        ArrayList<String> commands;
        ResultantAction result;
        String commandWord;

        if(settings.preGameModeEnabled()) {
            System.out.println("==Pre Game Mode==");
            
            while(isPreGame) {
                System.out.print(">> ");

                try {
                    userInputCommand = parser.getUserInput();
                }catch(IOException IOE) {
                    System.out.println(IOE.getMessage());
                    continue;
                }

                result = parser.parseCommand(userInputCommand, true);

                if(result.hasError()) {
                    System.out.println(result.getResponse());
                    continue;
                }

                commandWord = result.getCommandWord();

                if(commandWord.equals("quit")) {
                    System.out.println("Thanks for playing");
                    isPreGame = false;
                    break;
                }

                if(commandWord.equals("pregamehelp") || commandWord.equals("pregamemanual")) {
                    System.out.println(result.getResponse());
                    continue;
                }

                switch(commandWord) {
                    case "load":
                        // load game save here
                    case "new":
                        inGame = true;
                }

                inGameMode();
            }
        }else{
            inGame = true;
            inGameMode();
        }
    }

    /**
     * Enter the in-game mode.
     */
    private void inGameMode()
    {
        String userInputCommand;
        ArrayList<String> commands;
        ResultantAction result;
        String commandWord;

        System.out.println("--In Game Mode--");

        while(inGame) {
            System.out.print("> ");

            try {
                userInputCommand = parser.getUserInput();
            }catch(IOException IOE) {
                System.out.println(IOE.getMessage());
                continue;
            }

            result = parser.parseCommand(userInputCommand, false);

            if(result.hasError()) {
                System.out.println(result.getResponse());
                continue;
            }

            commandWord = result.getCommandWord();

            if(commandWord.equals("quit")) {
                inGame = false; // save before quitting?
                break;
            }

            System.out.println(result.getResponse());

            // other actions
        }
    }
}