package fr.rorocraft.practice.sumo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.task.SumoTimer;

public class SumoArenaManager {
	
    private List<SumoArena> arenas = new ArrayList<>();
    
	public SumoArenaManager() {
		// TODO Auto-generated constructor stub
	}

	public List<SumoArena> getArenas() {
		return arenas;
	}
	
	
	@SuppressWarnings("deprecation")
	public void joinArena(Player firstPlayer, Player secondPlayer, Main main) {
		SumoArena arena = getFreeArena();
		
		
		if( arena == null) {
			
			firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible pour l'instant".replace("&", "§"));
			secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Aucune arène disponible".replace("&", "§"));
			
		} else {
			
			arena.getPlayers().add(firstPlayer);
			arena.getPlayers().add(secondPlayer);
			
			arena.setState(PracticeState.STARTING);
			arena.setStarted(true);
			
			firstPlayer.teleport(arena.getLoc1());
			secondPlayer.teleport(arena.getLoc2());
			
			firstPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + secondPlayer.getName());
	 		secondPlayer.sendMessage("&7[&9Yeti&7lounge] &7 Tu as été téléporté vers une arène. Tu joue contre §4".replace("&", "§") + firstPlayer.getName());
	 		
	 		if(main.getSumoDuel().containsKey(firstPlayer)) {
	 			main.getSumoDuel().remove(firstPlayer);
	 		}
	 		
	 		if(main.getSumoDuel().containsKey(secondPlayer)) {
	 			main.getSumoDuel().remove(secondPlayer);
	 		}
	 		
	 		main.getSumoQueue().remove(firstPlayer);
	 		main.getSumoQueue().remove(secondPlayer);
	 		
	 		main.getFrozenPlayers().add(firstPlayer.getUniqueId());
	 		main.getFrozenPlayers().add(secondPlayer.getUniqueId());
	 		
	 		
	 		Bukkit.getScheduler().runTaskTimer(main, new SumoTimer(firstPlayer, secondPlayer, main), 20, 20);
	 		//  A continuer
		}
	}

	private SumoArena getFreeArena() {
		for(SumoArena arena : getArenas()) {
			if(!arena.isStarted() && arena.isState(PracticeState.WAITING)) {
				return arena;
			}
		}
		return null;
	}
	
	public SumoArena getArenaByPlayer(Player p) {
		 for(SumoArena arena  : getArenas()) {
			 if(arena.getPlayers().contains(p)) {
				 return arena;
			 }
		 }
		
		return null;
	}

}
