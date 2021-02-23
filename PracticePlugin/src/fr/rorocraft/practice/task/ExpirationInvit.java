package fr.rorocraft.practice.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.practice.Main;

public class ExpirationInvit extends BukkitRunnable {

	private Player p;
	private Player target;
	private Main main;

	public ExpirationInvit(Main main,Player p, Player target) {
		this.p =p;
		this.target = target;
		this.main = main;
	}

	@Override
	public void run() {
		
		if(main.getBuildUhcWaitingList().containsKey(p) && main.getBuildUhcWaitingList().get(p) == target) {
			
			main.getBuildUhcWaitingList().remove(p);
			p.sendMessage("§7[§9Yeti§7lounge] §7 Votre demande d'invitation pour §4" + target.getName() + "§7 a expiré");
			target.sendMessage("§7[§9Yeti§7lounge] §7 La demande d'invitation de §4" + p.getName() + "§7 a éxpiré");
		
		} else if(main.getNoDebuffWaitingList().containsKey(p) && main.getNoDebuffWaitingList().get(p) == target) {
			
			main.getNoDebuffWaitingList().remove(p);
			p.sendMessage("§7[§9Yeti§7lounge] §7 Votre demande d'invitation pour §4" + target.getName() + "§7 a expiré");
			target.sendMessage("§7[§9Yeti§7lounge] §7 La demande d'invitation de §4" + p.getName() + "§7 a éxpiré");
		
		} else if(main.getFactionWaitingList().containsKey(p) && main.getFactionWaitingList().get(p) == target) {
			
			main.getFactionWaitingList().remove(p);
			p.sendMessage("§7[§9Yeti§7lounge] §7 Votre demande d'invitation pour §4" + target.getName() + "§7 a expiré");
			target.sendMessage("§7[§9Yeti§7lounge] §7 La demande d'invitation de §4" + p.getName() + "§7 a éxpiré");
		
		} else if(main.getComboWaitingList().containsKey(p) && main.getComboWaitingList().get(p) == target) {
			
			main.getComboWaitingList().remove(p);
			p.sendMessage("§7[§9Yeti§7lounge] §7 Votre demande d'invitation pour §4" + target.getName() + "§7 a expiré");
			target.sendMessage("§7[§9Yeti§7lounge] §7 La demande d'invitation de §4" + p.getName() + "§7 a éxpiré");
		} else if(main.getSumoWaitingList().containsKey(p) && main.getSumoWaitingList().get(p) == target) {
			
			main.getSumoWaitingList().remove(p);
			
			p.sendMessage("§7[§9Yeti§7lounge] §7 Votre demande d'invitation pour §4" + target.getName() + "§7 a expiré");
			target.sendMessage("§7[§9Yeti§7lounge] §7 La demande d'invitation de §4" + p.getName() + "§7 a éxpiré");

		}

	}

}
