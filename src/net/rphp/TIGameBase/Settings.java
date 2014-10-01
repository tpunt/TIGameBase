package net.rphp.TIGameBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class parses the settings file and loads them into memory. It will then
 * validate the settings, and set sensible defaults if any settings are either
 * missing or have invalid values.
 */
public class Settings
{
    private HashMap<String, String> settings;
    private static Settings instance = null;

    /**
     * Parse the settings file and populate the settings hashmap with the values.
     * Also validate the settings and set sensible defaults if there are any unset settings.
     */
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

    /**
     * Uses a Singleton to ensure the settings are only loaded and validated once
     *
     * @return 	An instance of Settings
     */
    public static Settings loadSettings(String filename)// throws FileNotFoundException, IOException
    {
        if(instance == null)
            instance = new Settings(filename);

        return instance;
    }

    /**
     * Goes through all the settings to ensure that they're set to a value
     */
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

    /**
     * Check the pregame mode setting to see if it is enabled
     *
     * @return	whether the pregame mode is enabled or not
     */
    public boolean preGameModeEnabled()
    {
        return (settings.get("pregame").equals("true")) ? true : false;
    }
}