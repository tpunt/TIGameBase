package net.rphp.TIGameBase;

import java.io.IOException;
import java.util.ArrayList;

public class GameBase
{
	private Parser parser = new Parser();
	private boolean isPreGame = true;
	private boolean inGame = false;

	public static void main(String[] args)
	{
		GameBase gameBase = new GameBase();
	}

	public GameBase()
	{
		System.out.println("Welcome!\r\n");

		preGameMode();
	}

	private void preGameMode()
	{
		String userInputCommand;
		ArrayList<String> commands;
		ResultantAction result;
		String commandWord;

		System.out.println("===Pre Game Mode===");

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

			if(commandWord.equals("pregamehelp")) {
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

			System.out.println("===Pre Game Mode===");
		}
	}

	private void inGameMode()
	{
		String userInputCommand;
		ArrayList<String> commands;
		ResultantAction result;
		String commandWord;

		System.out.println("---In Game Mode---");

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

			// other actions
		}
	}
}