package net.rphp.TIGameBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Settings
{
	private HashMap<String, String> settings;
	private static Settings instance = null;

	private Settings(String filename)// throws ...
	{
		settings = new HashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;

			while((line = br.readLine()) != null) {
				if(line.matches("^\\s*|\\s*;.*"))
					continue;

				String[] setting = new String[2];
				setting = line.split("\\s*=\\s*");
				settings.put(setting[0], setting[1]);
			}

			br.close();
		}catch(FileNotFoundException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}catch(IOException e) {
			System.out.print("e");
		}
	}

	public static Settings loadSettings(String filename)
	{
		if(instance == null)
			instance = new Settings(filename);

		return instance;
	}

	public HashMap<String, String> getSettings()
	{
		return settings;
	}
}