package fr.rorocraft.practice.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {
	public static final Location LOC_EDIT = new Location(Bukkit.getWorld("world"), 32.531, 30.0, 128.447, 90.4f, 0.7f);
	public static final Location LOBBY = new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f);

	
	public  Location parseStringToLoc(String worldName, String s) {
		String[] loc = s.split("/");
		double X = Double.valueOf(loc[0]);
		double Y = Double.valueOf(loc[1]);
		double Z = Double.valueOf(loc[2]);
		float yaw = Float.valueOf(loc[3]);
		float pitch = Float.valueOf(loc[4]);
		
		return new Location(Bukkit.getWorld(worldName), X , Y , Z , yaw, pitch);
		
	}
	
	
	public static String parseLoctoString(Location loc) {
		return loc.getX() + "," + loc.getY() + "," + loc.getZ();
	}
	
	
}
