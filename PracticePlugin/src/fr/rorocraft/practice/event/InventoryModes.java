package fr.rorocraft.practice.event;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.builduhc.UhcKit;
import fr.rorocraft.practice.faction.FactionKit;
import fr.rorocraft.practice.nodebuff.NoDebuffKit;
import fr.rorocraft.practice.utils.ItemName;
import fr.rorocraft.practice.utils.LocationUtils;
import fr.rorocraft.practice.utils.MenuName;

public class InventoryModes implements Listener{

	private Main main;

	public InventoryModes(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null ) return;
		
		if(e.getInventory().getName().equalsIgnoreCase(MenuName.MENU_MODE)) {
			if(e.getCurrentItem() == null) {
				return;
			}
			
			if(!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
				return;
			}
		
			
			
		switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
		case ItemName.BUILD_UHC:
			if(main.getBuildUhcQueue().contains(p)) {
				main.getBuildUhcQueue().remove(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de quitter la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "BuildUhc"));
				p.closeInventory();
				
			}else {
			if(main.isInQueue(p)) {
				p.sendMessage(main.getPrefix() + "Tu es déjà dans un duel :/");
				return;
			}
			main.getBuildUhcQueue().add(p);
			p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de rejoindre la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "BuildUhc"));
			p.closeInventory();
			}
			break;
			
		case ItemName.NO_DEBUFF:
			if(main.getNoDebuffQueue().contains(p)) {
				main.getNoDebuffQueue().remove(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de quitter la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "NoDebuff"));
				p.closeInventory();
			} else {
				
				if(main.isInQueue(p)) {
					p.sendMessage(main.getPrefix() + "Tu es déjà dans un duel :/");
					return;
				}
				
				main.getNoDebuffQueue().add(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de rejoindre la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "NoDebuff"));
				p.closeInventory();
			}
            break;
       
		case ItemName.FACTION:
			if(main.getFactionQueue().contains(p)) {
				main.getFactionQueue().remove(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de quitter la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Faction"));
				p.closeInventory();
			} else {
				
				if(main.isInQueue(p)) {
					p.sendMessage(main.getPrefix() + "Tu es déjà dans un duel :/");
					return;
				}
				
				main.getFactionQueue().add(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de rejoindre la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Faction"));
				p.closeInventory();
			}
			break;
			
		case ItemName.COMBO:
			if(main.getComboQueue().contains(p)) {
				main.getComboQueue().remove(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de quitter la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Combo"));
				p.closeInventory();
				
			} else {
				
				if(main.isInQueue(p)) {
					p.sendMessage(main.getPrefix() + "Tu es déjà dans un duel :/");
					return;
				}
				
				main.getComboQueue().add(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de rejoindre la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Combo"));
				p.closeInventory();
				
			}
			break;
		
		case ItemName.SUMO:
			if(main.getSumoQueue().contains(p)) {
				main.getSumoQueue().add(p);
				
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de quitter la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Sumo"));
				p.closeInventory();
			} else {
				
				if(main.isInQueue(p)) {
					p.sendMessage(main.getPrefix() + "Tu es déjà dans un duel :/");
					return;
				}
				
				main.getSumoQueue().add(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous venez de rejoindre la file d'attente en &6&l%Mode%".replace("&", "§").replace("%Mode%", "Sumo"));
				p.closeInventory();
				
			}
			break;
            
		default:
			break;
		}
		
		e.setCancelled(true);
		
		
		
		
		
		
		
		} else if(e.getInventory().getName().equalsIgnoreCase(MenuName.EDIT_MODE)) {
			if(!e.getCurrentItem().hasItemMeta()) return;
			
		switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
		
		case ItemName.BUILD_UHC:
			p.closeInventory();
			new UhcKit().addKit(p, main);
			p.teleport(LocationUtils.LOC_EDIT);
			p.sendMessage(main.getPrefix() + "Voici ton kit, édite le");
			main.getBuildUhcEditing().add(p);
		break;
		
		case ItemName.NO_DEBUFF:
			p.closeInventory();
			new NoDebuffKit().addKit(p, main);
			p.teleport(LocationUtils.LOC_EDIT);
			p.sendMessage(main.getPrefix() + "Voici ton kit, édite le");
			main.getNoDebuffEditing().add(p);
		break;
		
		case ItemName.FACTION:
			p.closeInventory();
			new FactionKit().addkit(p, main);
			p.teleport(LocationUtils.LOC_EDIT);
			p.sendMessage(main.getPrefix() + "Voici ton kit, édite le");
			main.getFactionEditing().add(p);
			
		break;	
			
			
		}
		
			
			
			e.setCancelled(true);
		}
		
	}
}
