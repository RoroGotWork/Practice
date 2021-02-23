package fr.rorocraft.practice.builduhc;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.utils.KitMenu;


public class UhcArena {
	
	private Location loc1;
	private Location loc2;
	private List<Player> players;
	private PracticeState state;
	private boolean isStarted;
	private ArrayList<String> brokenBlocks;
	private ArrayList<Location> placedBlocks;
	private ArrayList<Location> dropedItem;
	private ArrayList<ItemStack> drops = new ArrayList<>();
	private Main main;
	

	public UhcArena( Location loc1, Location loc2, Main main) {
		
		this.loc1 = loc1;
		this.loc2 = loc2; 
		this.players = new ArrayList<>();
		this.setState(PracticeState.WAITING);
		this.setStarted(false);
		this.brokenBlocks = new ArrayList<>();
		this.placedBlocks =  new ArrayList<>();
		this.dropedItem = new ArrayList<>();
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

	public void addPlayer(Player p) {
		 players.add(p);
	}

	public boolean isState(PracticeState state) {
		return this.state == state;
	}

	public void setState(PracticeState state) {
		this.state = state;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public ArrayList<String> getBrokenBlocks() {
		return brokenBlocks;
	}

	public ArrayList<Location> getPlacedBlocks() {
		return placedBlocks;
	}


	public ArrayList<Location> getDropedItem() {
		return dropedItem;
	}


	public ArrayList<ItemStack> getDrops() {
		return drops;
	}


	public void checkWin(Player looser) {
		if(players.size() == 1) {
			
			setState(PracticeState.FINISHING);
			Player p = players.get(0);
			
			p.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez gagné le duel contre §4" + looser.getName());
			
			Bukkit.broadcastMessage("§7[§9Yeti§7lounge] §4" + p.getName() + "§7 a gané un duel contre §4" + looser.getName() + " en §6§l Builduhc" );
			
			if(looser.isOnline()) {
				looser.sendMessage("§7[§9Yeti§7lounge] §7 Vous avez perdu le duel contre §4" + p.getName());
				looser.teleport(new Location(Bukkit.getWorld("world"), 0.544, 25.0, 39.496, 0.4f, -1.4f));
				new KitMenu().addKit(looser);
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
			
			new KitMenu().addKit(p);
			
			looser.setGameMode(GameMode.ADVENTURE);
			p.setHealth(20);
			p.setFoodLevel(20);
			
			players.clear();
			
			restart();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void restart() {
           setState(PracticeState.ENDING);
		if(getPlacedBlocks() != null) {
			
			
		for(Location loc : getPlacedBlocks()) {
			Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc).setType(Material.AIR);
			Bukkit.getWorld(loc.getWorld().getName());
		}
		
		
		}
		
		
		if(getBrokenBlocks() != null) {
			
		for(String b : getBrokenBlocks()) {
			
			String[] blockdata = b.split("/");
			
			int id = Integer.parseInt(blockdata[0]);
			byte data = Byte.parseByte(blockdata[1]);
			World world =Bukkit.getWorld(blockdata[2]);
			int X = Integer.parseInt(blockdata[3]);
			int Y = Integer.parseInt(blockdata[4]);
			int Z = Integer.parseInt(blockdata[5]);
			
			world.getBlockAt(X, Y, Z).setTypeId(id);
			world.getBlockAt(X, Y, Z).setData(data);
			
		}
		
		for( Location loc : this.getDropedItem()) {
			
			for(Entity ent : loc.getChunk().getEntities()) {
				if(ent instanceof Item) {
					ent.remove();
				}
			}
		}
		
		for( Location loc : this.getDropedItem()) {
			
			for(Entity ent : loc.getChunk().getEntities()) {
				if(ent instanceof Item) {
					ent.remove();
				}
			}
		}
		
		for(Chunk c : getLoc1().getWorld().getLoadedChunks()) {
		 
		/* BlockState[] bs  = c.getTileEntities();
			for(BlockState b : bs) {
				if(b.getType() == Material.LAVA || b.getType() == Material.WATER || b.getBlock().getType() == Material.OBSIDIAN 
						|| b.getBlock().getType() == Material.COBBLESTONE || b.getBlock().getTypeId() == 5 || b.getBlock().isLiquid()
						) {
					if(b.getLocation().distance(getLoc1()) <= 100 || b.getLocation().distance(getLoc2()) <= 100) {
						b.setType(Material.AIR);					}
				}
			} */
			Location loc = new Location(c.getWorld(), c.getX(), 4, c.getZ());
			
			for(int x = 0; x < 16; x++) {
				
			for(int y = 0; y < 35; y++) {
				
			for(int z = 0; z < 16; z++) {
			Block b = c.getBlock(x, y, z);
			
			if(b.getLocation().distance(getLoc1()) <= 100 || b.getLocation().distance(getLoc2()) <= 100) {
			
			if(b.isLiquid() || b.getType() == Material.LAVA || b.getType() == Material.WATER || b.getType() == Material.OBSIDIAN 
					|| b.getType() == Material.COBBLESTONE )   
				 b.setType(Material.AIR);	
					
			
			}
			
			}
				
			}
				
			}
			
			}
			
		}
		
		
		getDrops().clear();
		getBrokenBlocks().clear();
		getPlacedBlocks().clear();
		
		setStarted(false);
		setState(PracticeState.WAITING);
		
	}
		
	}
	


