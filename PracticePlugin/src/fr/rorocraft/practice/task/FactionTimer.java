package fr.rorocraft.practice.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.faction.FactionArena;
import fr.rorocraft.practice.state.PracticeState;

public class FactionTimer implements Runnable {

	private Player firstPlayer;
	private Player secondPlayer;
	private Main main;
	private int timer = 5;
	public FactionTimer(Player firstPlayer, Player secondPlayer, Main main) {
		 this.firstPlayer = firstPlayer;
		 this.secondPlayer = secondPlayer;
		 this.main  = main;
	}
	@Override
	public void run() {
		if(main.getFactionArenaManager().getArenaByPlayer(firstPlayer) == null  || main.getFactionArenaManager().getArenaByPlayer(secondPlayer) == null) {
			
			if(main.getFactionArenaManager().getArenaByPlayer(firstPlayer) != null) {
				FactionArena arena = main.getFactionArenaManager().getArenaByPlayer(firstPlayer);
				
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
				
				
				firstPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				secondPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				
			} else if(main.getFactionArenaManager().getArenaByPlayer(secondPlayer) != null) {
				
				FactionArena arena = main.getFactionArenaManager().getArenaByPlayer(secondPlayer);
				
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
				
				
				firstPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				secondPlayer.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				
			}
			
		}
		
		
		if(timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1, 1);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1, 1);
			
			
		}
		
		
		if(timer == 0) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			
			FactionArena arena = main.getFactionArenaManager().getArenaByPlayer(firstPlayer);
			arena.setState(PracticeState.PLAYING);
			
			main.getFrozenPlayers().remove(firstPlayer.getUniqueId());
			main.getFrozenPlayers().remove(secondPlayer.getUniqueId());
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			
			firstPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			secondPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			
			firstPlayer.setGameMode(GameMode.ADVENTURE);
			secondPlayer.setGameMode(GameMode.ADVENTURE);
			
		}
		
		timer --;

	}

}
