package fr.rorocraft.practice.combo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.task.ComboTimer;

public class ComboArenaManager {
    private List<ComboArena> arenas = new ArrayList<>();
    
	public ComboArenaManager() {
		// TODO Auto-generated constructor stub
	}

	public List<ComboArena> getArenas() {
		return arenas;
	}

	@SuppressWarnings("deprecation")
	public void joinArena(Player firstPlayer, Player secondPlayer, Main main) {
		ComboArena arena = getFreeArena();
		
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
	 		
	 		if(main.getComboDuel().containsKey(firstPlayer)) {main.getComboDuel().remove(firstPlayer);}
	 		if(main.getComboDuel().containsKey(secondPlayer)) {main.getComboDuel().remove(secondPlayer);}
	 		
	 		main.getComboQueue().remove(firstPlayer);
	 		main.getComboQueue().remove(secondPlayer);
	 		
	 		main.getFrozenPlayers().add(firstPlayer.getUniqueId());
	 		main.getFrozenPlayers().add(secondPlayer.getUniqueId());
	 		
	 		new ComboKit().addKit(firstPlayer, main);
	 		new ComboKit().addKit(secondPlayer, main);
	 		
	 		Bukkit.getScheduler().runTaskTimer(main, new ComboTimer(firstPlayer, secondPlayer, main), 20, 20);
		}
		
	}
	
	public ComboArena getFreeArena() {
		for(ComboArena arena : getArenas()) {
			if(!arena.isStarted() && arena.isState(PracticeState.WAITING) ) {
				return arena;
			}
		}
		return null;
	}
	
	public ComboArena getArenaByPlayer(Player p) {
		for(ComboArena arena : getArenas()) {
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		
		return null;
	}
	
}
