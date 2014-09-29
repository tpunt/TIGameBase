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

	private Settings(String filename)// throws FileNotFoundException, IOException
	{
		settings = new HashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String[] setting = new String[2];

			while((line = br.readLine()) != null) {
				if(line.matches("^\\s*|\\s*;.*"))
					continue;

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

		validateSettings();
	}

	public static Settings loadSettings(String filename)// throws FileNotFoundException, IOException
	{
		if(instance == null)
			instance = new Settings(filename);

		return instance;
	}

	private void validateSettings()
	{
		HashMap<String, String> defaultSettings = new HashMap<String, String>();

		defaultSettings.put("pregame", "true");

		for(String settingName : defaultSettings.keySet()) {
			if(!settings.containsKey(settingName)) {
				settings.put(settingName, defaultSettings.get(settingName));
			}
		}
	}

	public boolean preGameModeEnabled()
	{
		return (settings.get("pregame").equals("true")) ? true : false;
	}
}