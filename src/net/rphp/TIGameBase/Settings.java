package net.rphp.TIGameBase;

import java.util.HashMap;

public class Settings
{
	private HashMap<String, String> settings;
	private static Settings instance = null;

	private Settings(String filename)// throws ...
	{
		// open file here
	}

	public Settings getInstance(String filename)
	{
		if(instance == null)
			instance = new Settings(filename);

		return instance;
	}

	private void ParseSettingsFile(String line)
	{
		
	}
}