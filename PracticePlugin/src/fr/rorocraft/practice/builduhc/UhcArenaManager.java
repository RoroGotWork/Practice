package fr.rorocraft.practice.builduhc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.task.BuildUhcTimer;

public class UhcArenaManager {
	private List<UhcArena> arenas = new ArrayList<>();

	public UhcArenaManager() {
		
	}
	
	public void addArena(UhcArena arena) {
		arenas.add(arena);
	}

	public List<UhcArena> getArenas() {
		return arenas;
	}
	
	@SuppressWarnings("deprecation")
	public void joinArena(Player firstPlayer, Player secondPlayer, Main main) {
		UhcArena arena = getFreeArena();
		
		if(arena == null) {
			firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible pour l'instant".replace("&", "§"));
			secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible".replace("&", "§"));
			
		} else {
		
		
		
		arena.addPlayer(firstPlayer);
		arena.addPlayer(secondPlayer);
		
		arena.setStarted(true);
		arena.setState(PracticeState.STARTING);
		
		firstPlayer.teleport(arena.getLoc1());
		secondPlayer.teleport(arena.getLoc2());
		
		
		firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + secondPlayer.getName());
		secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + firstPlayer.getName());
		
		new UhcKit().addKit(firstPlayer, main);
		new UhcKit().addKit(secondPlayer, main);
		
	   if(main.getBuildUhcDuel().containsKey(firstPlayer) ){
		   main.getBuildUhcDuel().remove(firstPlayer);
	   } else if(main.getBuildUhcDuel().containsKey(secondPlayer)) {
		   main.getBuildUhcDuel().remove(secondPlayer);
	   }
	   
	   main.getBuildUhcQueue().remove(firstPlayer);
	   main.getBuildUhcQueue().remove(secondPlayer);
	   
	   main.getFrozenPlayers().add(firstPlayer.getUniqueId());
	   main.getFrozenPlayers().add(secondPlayer.getUniqueId());
	   
	   Bukkit.getScheduler().runTaskTimer(main, new BuildUhcTimer(firstPlayer, secondPlayer, main), 20, 20);
	   
		}
		
	}
	
	public UhcArena getFreeArena() {
		
		for(UhcArena arena : arenas) {
			
			if(!arena.isStarted()) {
				
				return arena;
			}
		}
		return null;
	}
	
	public UhcArena getArenaByPlayer(Player p) {
		for(UhcArena arena : getArenas() ) {
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		return null;
	}
	

}
