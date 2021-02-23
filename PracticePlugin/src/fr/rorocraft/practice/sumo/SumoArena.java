package fr.rorocraft.practice.sumo;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.utils.LocationUtils;

public class SumoArena {

	private Location loc1;
	private Location loc2;
	private ArrayList<Player> players;
	private boolean isStarted;
	private PracticeState state;
	private Main main;

	public SumoArena(Location loc1, Location loc2, Main main) {
		 this.loc1 = loc1;
		 this.loc2 = loc2;
		 this.players = new ArrayList<>();
		 this.setStarted(false);
		 this.setState(PracticeState.WAITING);
		 this.main = main;
		 
	}

	public Location getLoc1() {
		return loc1;
	}

	public Location getLoc2() {
		return loc2;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public void setState(PracticeState state) {
		this.state = state;
	}
	
	public boolean isState(PracticeState state) {
		return this.state == state;
	}
	
	
	public void checkWin(Player looser) {
		if(players.size() == 1) {
			
			setState(PracticeState.FINISHING);
			Player p = players.get(0);
			
			p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez gagné le duel contre §4" + looser.getName());
			
			Bukkit.broadcastMessage("§7[§9Yeti§7lounge] §4" + p.getName() + "§7 a gagné un duel contre §4" + looser.getName() + "§4 en §6§l NoDebuff" );
			
			if(looser.isOnline()) {
				looser.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez perdu le duel contre §4" + p.getName());
				looser.teleport(LocationUtils.LOBBY);
			}
			
			if(main.getFrozenPlayers().contains(p.getUniqueId())) {
				main.getFrozenPlayers().remove(p.getUniqueId());
			}
			
			if(main.getFrozenPlayers().contains(looser.getUniqueId())) {
				main.getFrozenPlayers().remove(looser.getUniqueId());
			}
			
			
			p.teleport(LocationUtils.LOBBY);
			
			p.setGameMode(GameMode.ADVENTURE);
			p.setHealth(20);
			p.setFoodLevel(20);
			
			
			looser.setGameMode(GameMode.ADVENTURE);
			p.setHealth(20);
			p.setFoodLevel(20);
			
			players.clear();
			
			restart();
		} }
		


	public void restart() {
	for(Entity ent : getLoc1().getChunk().getEntities()) {
		if(ent instanceof Item) {
			ent.remove();
		}
	}
	
	
	for(Entity ent : getLoc2().getChunk().getEntities()) {
		if(ent instanceof Item) {
			ent.remove();
		}
		
     }
	
	
	setState(PracticeState.WAITING);
	setStarted(false);
	
	
	}

}
