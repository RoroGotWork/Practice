package fr.rorocraft.practice.nodebuff;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;

public class NoDebuffArena {
	private Location loc1;
	private Location loc2;
	private List<Player> players;
	private PracticeState state;
	private boolean isStarted;
	private Main main;
	
	
	public NoDebuffArena(Location loc1, Location loc2, Main main) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.players = new ArrayList<>();
		this.setState(PracticeState.WAITING);
		this.setStarted(false);
		this.main = main;
	}


	public Location getLoc1() {
		return loc1;
	}
	
	public Location getLoc2() {
		return loc2;
	}


	public List<Player> getPlayers() {
		return players;
	}


	public void setState(PracticeState state) {
		this.state = state;
	}

	public boolean isState(PracticeState state) {
		return this.state == state;
	}


	public boolean isStarted() {
		return isStarted;
	}


	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	
	
			
			public void checkWin(Player looser) {
				if(players.size() == 1) {
					
					setState(PracticeState.FINISHING);
					Player p = players.get(0);
					int potion = 0;
					for(ItemStack it : looser.getInventory().getContents()) {
						
						if(it.getType() == Material.POTION) {
							potion ++;
						}
					}
					
					p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez gagné le duel contre §4" + looser.getName() + " §7il lui restait " + potion + "potions");
					
					Bukkit.broadcastMessage("§7[§9Yeti§7lounge] §4" + p.getName() + "§7 a gané un duel contre §4" + looser.getName() + " en §6§l NoDebuff" );
					
					if(looser.isOnline()) {
						 int pot = 0;  
								 
						for(ItemStack it : p.getInventory().getContents()) {
							
							if(it.getType() == Material.POTION) {
								pot ++;
							}
						}
						looser.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez perdu le duel contre §4" + p.getName() + " §7il lui restait " + pot + "potions");
						looser.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
					}
					
					if(main.getFrozenPlayers().contains(p.getUniqueId())) {
						main.getFrozenPlayers().remove(p.getUniqueId());
					}
					
					if(main.getFrozenPlayers().contains(looser.getUniqueId())) {
						main.getFrozenPlayers().remove(looser.getUniqueId());
					}
					
					p.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
					
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

