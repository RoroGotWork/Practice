package fr.rorocraft.practice.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.builduhc.UhcArena;
import fr.rorocraft.practice.combo.ComboArena;
import fr.rorocraft.practice.faction.FactionArena;
import fr.rorocraft.practice.nodebuff.NoDebuffArena;
import fr.rorocraft.practice.state.PracticeState;
import fr.rorocraft.practice.sumo.SumoArena;

public class EventManager implements Listener {

	private Main main;
	private Map<Player, Long> cooldown = new HashMap<>();

	public EventManager(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();

		if(main.getUhcArenaManager().getArenaByPlayer(p) != null) {
			UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(p);
			if(arena.isStarted() && arena.isState(PracticeState.PLAYING)) {
			String info = b.getTypeId() +"/" + b.getData() + "/" + b.getWorld().getName() + "/" + b.getX() + "/" + b.getY() +"/" + b.getZ();
			arena.getBrokenBlocks().add(info);

            arena.getDropedItem().add(b.getLocation());
            
			} 
			
		}
		else if(!p.isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();

		if(main.getUhcArenaManager().getArenaByPlayer(p) != null) {
			UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(p);
			if(arena.isStarted() && arena.isState(PracticeState.PLAYING)) {
			arena.getPlacedBlocks().add(new Location(b.getWorld(), b.getX(), b.getY(), b.getZ()));
			} 
				
		}
		else if(!p.isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p =e.getPlayer();
		if(main.getFrozenPlayers().contains(p.getUniqueId())) {
			e.setTo(e.getFrom());
		}
		
		Material b = p.getLocation().getBlock().getType();
		
		if(b == Material.WATER) return;
		if(main.getSumoArenaManager().getArenaByPlayer(p) != null) {
			SumoArena arena = main.getSumoArenaManager().getArenaByPlayer(p);
			
			arena.getPlayers().remove(p);
			arena.checkWin(p);
		}
	}
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		
		Player looser = e.getEntity();
		
		if(!(e.getEntity().getKiller() instanceof Player)) { 
			
			if(main.getUhcArenaManager().getArenaByPlayer(looser) != null) {
				UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(looser);
				arena.getPlayers().remove(looser);
				arena.checkWin(looser);
			}
			
			
			return;
			}
		
		Player winner = e.getEntity().getKiller();
		
		
		if(main.getUhcArenaManager().getArenaByPlayer(looser) == main.getUhcArenaManager().getArenaByPlayer(winner)) {
			UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(winner);
			arena.getPlayers().remove(looser);
			arena.checkWin(looser);
		}
		
		// No Debuff
		if(main.getNoDebuffArenaManager().getArenaByPlayer(looser) != null) {
		   NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(looser);
		   arena.getPlayers().remove(looser);
			arena.checkWin(looser);
				}
		
		if(main.getFactionArenaManager().getArenaByPlayer(looser) != null) {
			FactionArena arena = main.getFactionArenaManager().getArenaByPlayer(looser);
			arena.getPlayers().remove(looser);
			arena.checkWin(looser);
		}
		
		if(main.getComboArenaManager().getArenaByPlayer(looser) != null) {
			ComboArena arena = main.getComboArenaManager().getArenaByPlayer(looser);
			arena.getPlayers().remove(looser);
			arena.checkWin(looser);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(main.getFrozenPlayers().contains(p.getUniqueId())) {
			main.getFrozenPlayers().remove(p.getUniqueId());
		}
		
		// Build uhc
		if(main.getUhcArenaManager().getArenaByPlayer(p) != null) {
			UhcArena arena = main.getUhcArenaManager().getArenaByPlayer(p);
			arena.getPlayers().remove(p);
			arena.checkWin(p);
		}
		
		// No Debuff
		if(main.getNoDebuffArenaManager().getArenaByPlayer(p) != null) {
		NoDebuffArena arena = main.getNoDebuffArenaManager().getArenaByPlayer(p);
		arena.getPlayers().remove(p);
		arena.checkWin(p);
		}
		
		
		if(main.getFactionArenaManager().getArenaByPlayer(p) != null) {
			FactionArena arena = main.getFactionArenaManager().getArenaByPlayer(p);
			arena.getPlayers().remove(p);
			arena.checkWin(p);
		}
		
		if(main.getComboArenaManager().getArenaByPlayer(p) != null) {
			ComboArena arena = main.getComboArenaManager().getArenaByPlayer(p);
			arena.getPlayers().remove(p);
			arena.checkWin(p);
		}
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player p =(Player) e.getEntity();
		
		if(e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			
			// builduhc
			if(main.getUhcArenaManager().getArenaByPlayer(p) == main.getUhcArenaManager().getArenaByPlayer(damager)
					
					
					|| main.getNoDebuffArenaManager().getArenaByPlayer(p) ==  main.getNoDebuffArenaManager().getArenaByPlayer(damager)
					|| main.getSumoArenaManager().getArenaByPlayer(p) == main.getSumoArenaManager().getArenaByPlayer(damager)
					|| main.getFactionArenaManager().getArenaByPlayer(p) == main.getFactionArenaManager().getArenaByPlayer(damager)
					|| main.getComboArenaManager().getArenaByPlayer(p) == main.getComboArenaManager().getArenaByPlayer(damager))
				
			{
				
				
			} else {
				e.setCancelled(true);
			}
			
			
			if(main.getSumoArenaManager().getArenaByPlayer(p) != null &&
					main.getSumoArenaManager().getArenaByPlayer(p) == main.getSumoArenaManager().getArenaByPlayer(damager)) {
				e.setDamage(0);
			}
			
		}
	}
	
	 @EventHandler
	public void onInteract(PlayerInteractEvent e) {
		 Player p = e.getPlayer();
		 
		 if(main.getFactionArenaManager().getArenaByPlayer(p) == null && main.getUhcArenaManager().getArenaByPlayer(p) == null
				 && main.getNoDebuffArenaManager().getArenaByPlayer(p) == null) {
			 e.setCancelled(true);
			 return;
		 }
		 
		if(e.getItem().getType() == Material.ENDER_PEARL) {
			if(main.getNoDebuffArenaManager().getArenaByPlayer(p) != null  || main.getFactionArenaManager().getArenaByPlayer(p) != null ) {
				
			
			
		
		
			if(!cooldown.containsKey(p)) {
				cooldown.put(p, System.currentTimeMillis());
			} else {
				long secondsLeft = (cooldown.get(p) / 1000 + 10) - (System.currentTimeMillis() / 1000);
				
				if(secondsLeft > 0) {
					p.sendMessage(main.getPrefix() + "Vous ne pouvez pas encore envoyer d'ender pearl");
					e.setCancelled(true);
				} else {
					cooldown.remove(p);
					cooldown.put(p, System.currentTimeMillis());
				}
			}
			
			return; 
			}
			
			e.setCancelled(true);
			
		}
		
		
		
		
	}
	
	 
	 public void onDrop(PlayerDropItemEvent e) {
		 Player p = e.getPlayer();
		 if(main.getFactionArenaManager().getArenaByPlayer(p) == null && main.getUhcArenaManager().getArenaByPlayer(p) == null
				 && main.getNoDebuffArenaManager().getArenaByPlayer(p) == null || main.getComboArenaManager().getArenaByPlayer(p) == null) {
			 e.setCancelled(true);
		 }
		}
		
	 

}
