package fr.rorocraft.practice.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;

public class GameManager extends BukkitRunnable {

	private Main main;

	public GameManager(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		if(main.getBuildUhcQueue().size() >= 2) {
		Player firstPlayer = main.getBuildUhcQueue().get(0);
		
		if(main.getBuildUhcDuel().containsKey(firstPlayer)) {
			Player secondPlayer = main.getBuildUhcDuel().get(firstPlayer);
			main.getUhcArenaManager().joinArena(firstPlayer, secondPlayer, main);
		} else {
			Player secondPlayer = main.getBuildUhcQueue().get(1);
			main.getUhcArenaManager().joinArena(firstPlayer, secondPlayer, main);
		}
		}

	    if(main.getNoDebuffQueue().size() >= 2) {
	    	Player firstPlayer = main.getNoDebuffQueue().get(0);
	    	
	    	if(main.getBuildUhcDuel().containsKey(firstPlayer)) {
	    		Player secondPlayer = main.getBuildUhcDuel().get(firstPlayer);
	    		main.getNoDebuffArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	} else {
	    		Player secondPlayer = main.getNoDebuffQueue().get(1);
	    		main.getNoDebuffArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	}
	    	
	    }
	    if(main.getFactionQueue().size() >= 2) {
	    	Player firstPlayer = main.getFactionQueue().get(0);
	    	
	    	if(main.getFactionDuel().containsKey(firstPlayer)) {
	    		Player secondPlayer = main.getFactionDuel().get(firstPlayer);
	    		
	    		main.getFactionArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	} else {
	    		Player secondPlayer = main.getFactionQueue().get(1);
	    		
	    		main.getFactionArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	}
	    }
	    
	    if(main.getComboQueue().size() >= 2){
	    	Player firstPlayer = main.getComboQueue().get(0);
	    	
	    if(main.getComboDuel().containsKey(firstPlayer)){
	    	Player secondPlayer = main.getComboDuel().get(firstPlayer);
	    	
	    	main.getComboArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	} else {
	    		Player secondPlayer = main.getComboQueue().get(1);
	    		
	    		main.getComboArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	}
	    }
	    
	    if(main.getSumoQueue().size() >= 2) {
	    	Player firstPlayer = main.getSumoQueue().get(0);
	    	
	    	if(main.getSumoDuel().containsKey(firstPlayer)) {
	    		Player secondPlayer  = main.getSumoDuel().get(firstPlayer);
	    		
	    		main.getSumoArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	} else {
	    		
	    		Player secondPlayer = main.getSumoQueue().get(1);
	    		
	    		main.getSumoArenaManager().joinArena(firstPlayer, secondPlayer, main);
	    	}
	    
	    }
	    
	    
	}

}
