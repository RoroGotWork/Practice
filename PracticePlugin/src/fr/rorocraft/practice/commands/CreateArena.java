package fr.rorocraft.practice.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.practice.Main;
import fr.rorocraft.practice.utils.ArenaCreator;

public class CreateArena implements CommandExecutor {

	private Main main;

	public CreateArena(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if(!(s instanceof Player)) return true;
		Player p =(Player) s;
		
		
		if(!p.isOp()) return true;
		
		if(args.length == 0 ||  args.length == 1 || args.length == 2 ) {
			p.sendMessage(main.getPrefix() + " Vous devez faire /createarena <nom du mode de jeu> <pos1> <pos2> ");
		    return true;
		}
		
		 if(args[0].equalsIgnoreCase("builduhc")) {
			try {
				new ArenaCreator().createArena(main.buildUhcFile, main.buildUhcConfig, main, args[1], args[2], p.getWorld().getName());
				p.sendMessage(main.getPrefix() + "L'arène a été crée");
			} catch (Exception e) {
				p.sendMessage(main.getPrefix() + "L'arène n'a pas pu être crée");
				e.printStackTrace();
			} 
		 } else if(args[0].equalsIgnoreCase("nodebuff")) {
			 try {
				new ArenaCreator().createArena(main.noDebuffFile, main.noDebuffConfig, main, args[1], args[2], p.getWorld().getName());
				p.sendMessage(main.getPrefix() + "L'arène a été crée");
			} catch (Exception e) {
				p.sendMessage(main.getPrefix() + "L'arène n'a pas pu être crée");
				e.printStackTrace();
			}
		 } else if (args[0].equalsIgnoreCase("combo")) {
			 try {
				new ArenaCreator().createArena(main.comboFile, main.comboConfig, main, args[1], args[2], p.getWorld().getName());
				p.sendMessage(main.getPrefix() + "L'arène a été crée");
			} catch (Exception e) {
				p.sendMessage(main.getPrefix() + "L'arène n'a pas pu être crée");
				e.printStackTrace();
			}
		 } else if (args[0].equalsIgnoreCase("faction")) {
			 try {
					new ArenaCreator().createArena(main.factionFile, main.factionConfig, main, args[1], args[2], p.getWorld().getName());
					p.sendMessage(main.getPrefix() + "L'arène a été crée");
				} catch (Exception e) {
					p.sendMessage(main.getPrefix() + "L'arène n'a pas pu être crée");
					e.printStackTrace();
				}
		 } else if(args[0].equalsIgnoreCase("sumo")) {
			 
			 try {
					new ArenaCreator().createArena(main.sumoFile, main.sumoConfig, main, args[1], args[2], p.getWorld().getName());
					p.sendMessage(main.getPrefix() + "L'arène a été crée");
				} catch (Exception e) {
					p.sendMessage(main.getPrefix() + "L'arène n'a pas pu être crée");
					e.printStackTrace();
				}
		 }

		
		
		return false;
	}

}
