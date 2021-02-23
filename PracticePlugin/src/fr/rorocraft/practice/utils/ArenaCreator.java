package fr.rorocraft.practice.utils;

import java.io.File;
import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.rorocraft.practice.Main;

public class ArenaCreator {

	public ArenaCreator() {
		// TODO Auto-generated constructor stub
	}

	
	
	public void createArena(File file, YamlConfiguration config, Main main, String pos1, String pos2, String world) throws Exception {
	     Random r = new Random();
	     int nombreR = r.nextInt(200000);
		  
	     if(config.get("arenas.arena-" +nombreR) == null) {
	    	 config.set("arenas.arena-" + nombreR + ".world", world);
	    	 config.set("arenas.arena-" + nombreR + ".pos1", pos1 );
	    	 config.set("arenas.arena-" + nombreR + ".pos2", pos2 );
	     }
	     
	     config.save(file);
	}
	
	
}
