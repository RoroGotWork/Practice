package fr.rorocraft.practice.nodebuff;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.task.NoDebuffTimer;

public class NoDebuffArenaManager {
	private List<NoDebuffArena> arenas = new ArrayList<>();
        
	public NoDebuffArenaManager() {
			// TODO Auto-generated constructor stub
		}
        
	public void addArena(NoDebuffArena arena) {
		this.arenas.add(arena);
	}
	
	public List<NoDebuffArena> getArenas() {
		return arenas;
	}
	
	
	@SuppressWarnings("deprecation")
	public void joinArena(Player  firstPlayer, Player secondPlayer, Main main) {
		     NoDebuffArena arena = getFreeArena();
		     if(arena == null) {
		    	firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible pour l'instant".replace("&", "§"));
				secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible".replace("&", "§"));
		     } else {
		    	 

		 		arena.getPlayers().add(firstPlayer);
		 		arena.getPlayers().add(secondPlayer);
		 		
		 		arena.setStarted(true);
		 		arena.setState(PracticeState.STARTING);
		 		
		 		firstPlayer.teleport(arena.getLoc1());
		 		secondPlayer.teleport(arena.getLoc2());
		 		
		 		
		 		firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + secondPlayer.getName());
		 		secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + firstPlayer.getName());
		 		
		 		//  ajout des kits
		 		
		 		
		 		if(main.getNoDebuffDuel().containsKey(firstPlayer) ){
		 		   main.getNoDebuffDuel().remove(firstPlayer);
		 	   } else if(main.getBuildUhcDuel().containsKey(secondPlayer)) {
		 		   main.getNoDebuffDuel().remove(secondPlayer);
		 	   }
		 		
		 	   main.getNoDebuffQueue().remove(firstPlayer);
		 	   main.getNoDebuffQueue().remove(secondPlayer);
		 	   
		 	   main.getFrozenPlayers().add(firstPlayer.getUniqueId());
		 	   main.getFrozenPlayers().add(secondPlayer.getUniqueId());
		 		
		 	   new NoDebuffKit().addKit(firstPlayer, main);
		 	   new NoDebuffKit().addKit(secondPlayer, main);
		 	  
		 	   
		 	   Bukkit.getScheduler().runTaskTimer(main,  new NoDebuffTimer(firstPlayer, secondPlayer, main), 20, 20);
		    	 	 
		     }
		
		}
	
	
	public NoDebuffArena getFreeArena() {
		
		for(NoDebuffArena arena : getArenas()) {
			if(!arena.isStarted() && arena.isState(PracticeState.WAITING)) {
				return arena;
			} 
			}
		return null;
	}
	
	public NoDebuffArena getArenaByPlayer(Player p) {
		for(NoDebuffArena arena : getArenas()){
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		return null;
	}
	
	
}
