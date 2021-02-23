package fr.rorocraft.practice.faction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.task.FactionTimer;

public class FactionArenaManager {
	private List<FactionArena> arenas = new ArrayList<>();

	public List<FactionArena> getArenas() {
		return arenas;
	}
	
	public void joinArena(Player firstPlayer, Player  secondPlayer, Main main) {
		FactionArena arena = getFreeArena();
		if(arena == null) {
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
	 		
	 		if(main.getFactionDuel().containsKey(firstPlayer)) {
	 			main.getFactionDuel().remove(firstPlayer);
	 		} else if(main.getFactionDuel().containsKey(secondPlayer)) {
	 			main.getFactionDuel().remove(secondPlayer);
	 		}
	 		
	 		main.getFactionQueue().remove(firstPlayer);
	 		main.getFactionQueue().remove(secondPlayer);
	 		
	 		main.getFrozenPlayers().add(firstPlayer.getUniqueId());
	 		main.getFrozenPlayers().add(secondPlayer.getUniqueId());
	 		
	 		// Ajout des kits
	 		new FactionKit().addkit(firstPlayer, main);
	 		new FactionKit().addkit(secondPlayer, main);
	 		
	 		Bukkit.getScheduler().runTaskTimer(main,  new FactionTimer(firstPlayer, secondPlayer, main),  20, 20);
		}
	}
	
	
	
	
	public FactionArena getFreeArena() {
		for(FactionArena arena : getArenas()) {
			if(!arena.isStarted() && arena.isState(PracticeState.WAITING)) {
				return arena;
			}
			
		}
		return null;
	}
	
	public FactionArena getArenaByPlayer(Player p) {
		
		for(FactionArena arena : getArenas()) {
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		
		return null;
	}

}
