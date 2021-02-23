package fr.rorocraft.practice.event;



import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.utils.ItemName;
import fr.rorocraft.practice.utils.KitMenu;
import fr.rorocraft.practice.utils.MenuName;




public class PlayerJoinServer implements Listener {
	
	private Main main;
	KitMenu kit = new KitMenu();

	public PlayerJoinServer(Main main) {
	this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		
		if(!p.isOp()) {
			if(p.getGameMode() != GameMode.ADVENTURE) {
				p.setGameMode(GameMode.ADVENTURE);
			}
		}
		
		
		
		p.teleport(new Location(p.getWorld(), 0.544, 25.0, 39.496, 0.4f, -1.4f));
		
		
		
		kit.addKit(p);
		
		
		
		
				
	}
	
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack it = e.getItem();
		if((e.getAction() == Action.LEFT_CLICK_AIR)|| (e.getAction() == Action.LEFT_CLICK_BLOCK) ) return;
		
		
		try {
		if( it.getItemMeta().getDisplayName().equalsIgnoreCase(ItemName.EPEE_JOIN)) {
			kit.addMenuDuel(p, main, MenuName.MENU_MODE);
			
		}
		
		if(it.getItemMeta().getDisplayName().equalsIgnoreCase(ItemName.EDIT_KIT)) {
			kit.addMenuEdit(p);
		}
		
		} catch (Exception ex) {
			
		}
		
	}
}
