package fr.rorocraft.practice.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.utils.KitMenu;
import fr.rorocraft.practice.utils.LocationUtils;


public class Edit implements CommandExecutor {
	
	private Main main;

	public Edit(Main main) {
		this.main =main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if(!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if(args.length == 0) {
			p.sendMessage("§7[§9Yeti§7lounge] §7 Faites /edit leave");
			return true;
		}
		
		
		
		if(args[0].equalsIgnoreCase("leave")) {
			if(main.getBuildUhcEditing().contains(p)) {
				main.dataConfig.set("kit." + p.getUniqueId()  + ".builduhc.items" , p.getInventory().getContents());
				main.dataConfig.set("kit." + p.getUniqueId()  + ".builduhc.armor" , p.getInventory().getArmorContents());
				try {
					main.dataConfig.save(main.dataFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				p.sendMessage("§7[§9Yeti§7lounge] §7 Ton kit a été update pour les combats à venir");
				main.getBuildUhcEditing().remove(p);
				
				new KitMenu().addKit(p);
				
				p.teleport(LocationUtils.LOBBY);
				
			} else if (main.getNoDebuffEditing().contains(p)) {
				main.dataConfig.set("kit." + p.getUniqueId()  + ".nodebuff.items" , p.getInventory().getContents());
				main.dataConfig.set("kit." + p.getUniqueId()  + ".nodebuff.armor" , p.getInventory().getArmorContents());
				
				try {
					main.dataConfig.save(main.dataFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new KitMenu().addKit(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Ton kit a été update pour les combats à venir");
				main.getNoDebuffEditing().remove(p);
			
				p.teleport(LocationUtils.LOBBY);
				
			} else if(main.getFactionEditing().contains(p)) {
				
				main.dataConfig.set("kit." + p.getUniqueId()  + ".faction.items" , p.getInventory().getContents());
				main.dataConfig.set("kit." + p.getUniqueId()  + ".faction.armor" , p.getInventory().getArmorContents());
				
				try {
					main.dataConfig.save(main.dataFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				new KitMenu().addKit(p);
				p.sendMessage("§7[§9Yeti§7lounge] §7 Ton kit a été update pour les combats à venir");
				main.getFactionEditing().remove(p);
				
				p.teleport(LocationUtils.LOBBY);
			}

			else {
				p.sendMessage("§7[§9Yeti§7lounge] §7 Vous n'aviez pas eu de kit à éditer");
			}
			
		}
		
		return false;
	}

}
