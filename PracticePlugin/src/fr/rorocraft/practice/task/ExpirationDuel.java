package fr.rorocraft.practice.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;

public class ExpirationDuel  extends BukkitRunnable{

	private Main main;
	private Player p;

	public ExpirationDuel(Main main, Player p) {
		this.main = main;
		this.p = p; 
	}

	@Override
	public void run() {
		if(main.getDuelMenu().containsKey(p)) {		
			main.getDuelMenu().remove(p);
		}
	}

}
