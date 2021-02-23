package fr.rorocraft.practice.task;



import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.builduhc.UhcArena;
import fr.rorocraft.practice.state.PracticeState;

public class BuildUhcTimer extends BukkitRunnable {

	private Player firstPlayer;
	private Player secondPlayer;
	private int timer = 5;
	private Main main;

	public BuildUhcTimer(Player firstPlayer, Player secondPlayer, Main main) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.main = main;
	}

	@Override
	public void run() {
		if(main.getUhcArenaManager().getArenaByPlayer(firstPlayer) == null || main.getUhcArenaManager().getArenaByPlayer(secondPlayer) == null) {
			
			if(main.getUhcArenaManager().getArenaByPlayer(firstPlayer) != null) {
				
				UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(firstPlayer);
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
			
			
			} else if(main.getUhcArenaManager().getArenaByPlayer(secondPlayer) != null) {
				
				UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(firstPlayer);
				arena.setStarted(false);
				arena.setState(PracticeState.WAITING);
				arena.getPlayers().clear();
				
				
			}
			return;
			
		}
		
		
		if(timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1 ) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1F, 1F);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.ORB_PICKUP, 1F, 1F);
		}

		if(timer == 0) {
			firstPlayer.setLevel( timer);
			secondPlayer.setLevel(timer);
			
			
			UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(firstPlayer);
			arena.setState(PracticeState.PLAYING);
			
			main.getFrozenPlayers().remove(firstPlayer.getUniqueId());
			main.getFrozenPlayers().remove(secondPlayer.getUniqueId());
			
			firstPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			secondPlayer.playSound(firstPlayer.getLocation(), Sound.EXPLODE, 1F, 1F);
			
			firstPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			secondPlayer.sendMessage("§7[§9Yeti§7lounge] §7 Lancement de la partie");
			
			firstPlayer.setGameMode(GameMode.SURVIVAL);
			secondPlayer.setGameMode(GameMode.SURVIVAL);
		}
		timer --;
	}

}
