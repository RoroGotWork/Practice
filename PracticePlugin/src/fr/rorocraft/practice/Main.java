package fr.rorocraft.practice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.rorocraft.practice.builduhc.UhcArena;
import fr.rorocraft.practice.builduhc.UhcArenaManager;
import fr.rorocraft.practice.combo.ComboArena;
import fr.rorocraft.practice.combo.ComboArenaManager;
import fr.rorocraft.practice.commands.CreateArena;
import fr.rorocraft.practice.commands.Duel;
import fr.rorocraft.practice.commands.Edit;
import fr.rorocraft.practice.commands.Hub;
import fr.rorocraft.practice.event.EventManager;
import fr.rorocraft.practice.event.InventoryModes;
import fr.rorocraft.practice.event.PlayerJoinServer;
import fr.rorocraft.practice.faction.FactionArena;
import fr.rorocraft.practice.faction.FactionArenaManager;
import fr.rorocraft.practice.nodebuff.NoDebuffArena;
import fr.rorocraft.practice.nodebuff.NoDebuffArenaManager;
import fr.rorocraft.practice.scoreboard.ScoreboardManager;
import fr.rorocraft.practice.sumo.SumoArena;
import fr.rorocraft.practice.sumo.SumoArenaManager;
import fr.rorocraft.practice.task.GameManager;
import fr.rorocraft.practice.utils.LocationUtils;


public class Main extends JavaPlugin{
	
	public List<UUID> frozen = new ArrayList<>();
	
	public File dataFile;
	public YamlConfiguration dataConfig;
	
	private Map<Player, Player> duelMenu = new HashMap<>();
			
	
	public File buildUhcFile;
	public  YamlConfiguration buildUhcConfig;
	private List<Player> buildUhcQueue = new ArrayList<>();
	private Map<Player, Player> buildUhcDuel = new HashMap<>();
	private Map<Player, Player> buildUhcWaitingList = new HashMap<>();
	private List<Player> buildUhcEditing = new ArrayList<Player>(); 
	private UhcArenaManager uhcArenaManager;
	
	public File noDebuffFile;
	public YamlConfiguration noDebuffConfig;
	private List<Player> noDebuffQueue = new ArrayList<>();
	private Map<Player, Player> noDebuffDuel = new HashMap<>();
	private Map<Player, Player> noDebuffWaitingList = new HashMap<>();
	private List<Player> noDebuffEditing = new ArrayList<>();
	private NoDebuffArenaManager noDebuffArenaManager;
	
	
	public File factionFile;
	public YamlConfiguration factionConfig;
	private List<Player> factionQueue = new ArrayList<>();
	private Map<Player, Player> factionDuel = new HashMap<>();
	private Map<Player, Player> factionWaitingList = new HashMap<>();
	private List<Player> factionEditing = new ArrayList<>();
    private FactionArenaManager factionArenaManager;
	
	public File comboFile;
	public YamlConfiguration comboConfig;
	private List<Player> comboQueue = new ArrayList<>();
	private Map<Player, Player> comboDuel = new HashMap<>();
	private Map<Player, Player> comboWaitingList = new HashMap<>();
	private List<Player> comboEditing = new ArrayList<>();
	private ComboArenaManager comboArenaManager;
	
	public File sumoFile;
	public YamlConfiguration sumoConfig;
	private List<Player> sumoQueue = new ArrayList<>();
	private Map<Player, Player> sumoDuel = new HashMap<>();
	private Map<Player, Player> sumoWaitingList = new HashMap<>();
	private SumoArenaManager sumoArenaManager;
			

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		if(!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		
		try {
			enableData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uhcArenaManager = new UhcArenaManager();
		enableBuildUhc();
		
		noDebuffArenaManager =new NoDebuffArenaManager();
		enableNoDebuff();
		
		
		factionArenaManager = new FactionArenaManager();
		enableFaction();
				
		
		comboArenaManager = new ComboArenaManager();
		enableCombo();
		
		sumoArenaManager  = new SumoArenaManager();
		enableSumo();
		
		// Enregistrement des events
		registerEvents();
		
		
		registerCommands();
		
		
		Bukkit.getScheduler().runTaskTimer(this, new GameManager(this),  20 * 10, 20 * 3);
		Bukkit.getScheduler().runTaskTimer(this, new ScoreboardManager(this) , 20, 20);
		System.out.println("[PracticePlugin] Le plugin est pret");
		super.onEnable();
	}
	
	

	



	@Override
	public void onDisable() {
		
		if(getUhcArenaManager().getArenas() != null) {
			
			
		for(UhcArena arena : getUhcArenaManager().getArenas()) {
			arena.restart();
		}
		
		
		}
		System.out.println("[PracticePlugin] Le plugin s'est eteint");
		super.onDisable();
	}
	
	
	public boolean isInQueue(Player p) {
		if(getBuildUhcQueue().contains(p)) return true;
		if(getNoDebuffQueue().contains(p)) return true;
		if(getFactionQueue().contains(p))  return true;
		if(getComboQueue().contains(p)) return true;
		if(getSumoQueue().contains(p)) return true;
		
  		return false;
	}
	
	public boolean isInGame(Player p) {
		if(getUhcArenaManager().getArenaByPlayer(p) != null) return true;
		if(getNoDebuffArenaManager().getArenaByPlayer(p) != null) return true;
		if(getFactionArenaManager().getArenaByPlayer(p) != null) return true;
		if(getComboArenaManager().getArenaByPlayer(p) != null) return true;
		if(getSumoArenaManager().getArenaByPlayer(p) != null) return true;
		
		return false;
	}
	
	
	public List<UUID> getFrozenPlayers() {
		return frozen;
	}
	
	public String getPrefix() {
		return "§7[§9Yeti§7lounge] §7 ";
		
	}

	
	// Build uhc
	public UhcArenaManager getUhcArenaManager() {
		return uhcArenaManager;
	}

	public List<Player> getBuildUhcQueue() {
		return buildUhcQueue;
	}
	
	public Map<Player, Player> getBuildUhcDuel(){
		return buildUhcDuel;
	}
	
	public Map<Player, Player> getBuildUhcWaitingList(){
		return buildUhcWaitingList;
	}
	
	
	public List<Player> getBuildUhcEditing() {
		return buildUhcEditing;
	}


	
	
	
	


    // No debuff
	public NoDebuffArenaManager getNoDebuffArenaManager() {
		return noDebuffArenaManager;
	}
	
	public List<Player> getNoDebuffQueue() {
		return noDebuffQueue;
	}

	public Map<Player, Player> getNoDebuffDuel() {
		return noDebuffDuel;
	}

	public Map<Player, Player> getNoDebuffWaitingList() {
		return noDebuffWaitingList;
	}

	public List<Player> getNoDebuffEditing() {
		return noDebuffEditing;
	}







	public List<Player> getFactionQueue() {
		return factionQueue;
	}

	public Map<Player, Player> getFactionDuel() {
		return factionDuel;
	}


	public Map<Player, Player> getFactionWaitingList() {
		return factionWaitingList;
	}


	public List<Player> getFactionEditing() {
		return factionEditing;
	}

	public FactionArenaManager getFactionArenaManager() {
		return factionArenaManager;
	}







	public List<Player> getComboQueue() {
		return comboQueue;
	}

	public Map<Player, Player> getComboDuel() {
		return comboDuel;
	}

	public Map<Player, Player> getComboWaitingList() {
		return comboWaitingList;
	}

	public List<Player> getComboEditing() {
		return comboEditing;
	}

	public ComboArenaManager getComboArenaManager() {
		return comboArenaManager;
	}







	public List<Player> getSumoQueue() {
		return sumoQueue;
	}


	public Map<Player, Player> getSumoDuel() {
		return sumoDuel;
	}


	public Map<Player, Player> getSumoWaitingList() {
		return sumoWaitingList;
	}

	public SumoArenaManager getSumoArenaManager() {
		return sumoArenaManager;
	}



	
	

	public void enableData() throws IOException {
		dataFile = new File(this.getDataFolder() + File.separator + "data.yml");
		if(!dataFile.exists()) {
			dataFile.createNewFile();
		}
		dataConfig= YamlConfiguration.loadConfiguration(dataFile);
		
	}
	
	
	public void enableBuildUhc() {
		

		
		
		 buildUhcFile = new File(getDataFolder() + File.separator + "builduhc.yml");
			
			
			if(!buildUhcFile.exists()) {
				try {
					buildUhcFile.createNewFile();
					buildUhcConfig = YamlConfiguration.loadConfiguration(buildUhcFile);
					buildUhcConfig.save(buildUhcFile);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			} else {
				buildUhcConfig = YamlConfiguration.loadConfiguration(buildUhcFile);
			}
			
			
			
			
			if(buildUhcConfig.get("arenas") != null) {
				for(String s : buildUhcConfig.getConfigurationSection("arenas").getKeys(false)) {
					String world = buildUhcConfig.getString("arenas." + s + ".world");
					Location pos1 = new LocationUtils().parseStringToLoc(world, buildUhcConfig.getString("arenas." + s + ".pos1"));
					Location pos2 = new LocationUtils().parseStringToLoc(world, buildUhcConfig.getString("arenas." + s + ".pos2"));
					getUhcArenaManager().addArena(new UhcArena( pos1, pos2, this));
				} 
			}
		
	}
	
	
	private void enableNoDebuff() {
		noDebuffFile  = new File(this.getDataFolder() + File.separator + "nodebuff.yml");
		
		if(!noDebuffFile.exists()) {
			try {
				noDebuffFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		noDebuffConfig = YamlConfiguration.loadConfiguration(noDebuffFile);
		
		if(noDebuffConfig.get("arenas") != null) {
			for(String s : noDebuffConfig.getConfigurationSection("arenas").getKeys(false)) {
				String world = noDebuffConfig.getString("arenas." + s + ".world");
				Location pos1 = new LocationUtils().parseStringToLoc(world, noDebuffConfig.getString("arenas." + s + ".pos1"));
				Location pos2 = new LocationUtils().parseStringToLoc(world, noDebuffConfig.getString("arenas." + s + ".pos2"));
				getNoDebuffArenaManager().getArenas().add(new NoDebuffArena( pos1, pos2, this));
			}
		}
		
	}
	
	private void enableFaction() {
		factionFile = new File(this.getDataFolder() + File.separator + "faction.yml");
		
		if(!factionFile.exists()) {
			
			try {
				factionFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		factionConfig = YamlConfiguration.loadConfiguration(factionFile);
		
		if(factionConfig.get("arenas") != null) {
			for(String s : factionConfig.getConfigurationSection("arenas").getKeys(false)) {
				String world = factionConfig.getString("arenas." + s + ".world");
				Location pos1 = new LocationUtils().parseStringToLoc(world, factionConfig.getString("arenas." + s + ".pos1"));
				Location pos2 = new LocationUtils().parseStringToLoc(world, factionConfig.getString("arenas." + s + ".pos2"));
				getFactionArenaManager().getArenas().add(new FactionArena(pos1, pos2, this));
				
				
			}
			
			
		}
		
		}
		
	}
	
	
	public void enableCombo() {
   comboFile = new File(this.getDataFolder() + File.separator + "combo.yml");
		
		if(!comboFile.exists()) {
			
			try {
				comboFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		comboConfig = YamlConfiguration.loadConfiguration(comboFile);
		
		if(comboConfig.get("arenas") != null) {
			for(String s : comboConfig.getConfigurationSection("arenas").getKeys(false)) {
				String world = factionConfig.getString("arenas." + s + ".world");
				Location pos1 = new LocationUtils().parseStringToLoc(world, comboConfig.getString("arenas." + s + ".pos1"));
				Location pos2 = new LocationUtils().parseStringToLoc(world, comboConfig.getString("arenas." + s + ".pos2"));
				getComboArenaManager().getArenas().add(new ComboArena(pos1, pos2, this));
				
				
			}
			
			
		}
		
		}
	}
	
	

	private void enableSumo() {
		 sumoFile = new File(this.getDataFolder() + File.separator + "sumo.yml");
			
			if(!sumoFile.exists()) {
				
				try {
					sumoFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			sumoConfig = YamlConfiguration.loadConfiguration(sumoFile);
			
			if(sumoConfig.get("arenas") != null) {
				for(String s : sumoConfig.getConfigurationSection("arenas").getKeys(false)) {
					String world = sumoConfig.getString("arenas." + s + ".world");
					Location pos1 = new LocationUtils().parseStringToLoc(world, sumoConfig.getString("arenas." + s + ".pos1"));
					Location pos2 = new LocationUtils().parseStringToLoc(world, sumoConfig.getString("arenas." + s + ".pos2"));
					getSumoArenaManager().getArenas().add(new SumoArena(pos1, pos2, this));
					
					
				}
				
				
			}
			
			}
		
	}
	public Map<Player, Player> getDuelMenu() {
		return duelMenu;
	}







	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoinServer(this), this);
		this.getServer().getPluginManager().registerEvents(new InventoryModes(this), this);
		this.getServer().getPluginManager().registerEvents(new EventManager(this), this);
	}
	
	private void registerCommands() {
		this.getCommand("duel").setExecutor(new Duel(this));
		this.getCommand("hub").setExecutor(new Hub(this));
		this.getCommand("edit").setExecutor(new Edit(this));
		this.getCommand("createarena").setExecutor(new CreateArena(this));
		
	}
	

	
	
	
}
