package de.framedev.timer.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	// Load the Config! / Put in the onEnable
		public static void loadConfig() {
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveDefaultConfig();
	    }
		// Update the Config put in the onEnable
		public static void updateConfig() {
	        try {
	            if(new File(Main.getInstance().getDataFolder() + "/config.yml").exists()) {
	                boolean changesMade = false;
	                YamlConfiguration tmp = new YamlConfiguration();
	                tmp.load(Main.getInstance().getDataFolder() + "/config.yml");
	                for(String str : Main.getInstance().getConfig().getKeys(true)) {
	                    if(!tmp.getKeys(true).contains(str)) {
	                        tmp.set(str, Main.getInstance().getConfig().get(str));
	                        changesMade = true;
	                    }
	                }
	                if(changesMade){

	                    tmp.save(Main.getInstance().getDataFolder() + "/config.yml");
	                }
	            }
	        } catch (IOException | InvalidConfigurationException e) {
	            e.printStackTrace();
	        }
	    }

}
