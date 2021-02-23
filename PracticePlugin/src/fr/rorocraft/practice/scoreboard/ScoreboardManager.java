package fr.rorocraft.practice.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.rorocraft.practice.Main;


public class ScoreboardManager extends BukkitRunnable {

	private Main main;

	public ScoreboardManager(Main main) {
		this.main = main;
	}

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			org.bukkit.scoreboard.ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
			Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
			
			Objective obj = scoreboard.registerNewObjective("Joueurs", "dummy");
			obj.setDisplayName("&9&lYeti&flounge".replace("&", "§"));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			obj.getScore("§7-").setScore(7);
			
			obj.getScore("&7Joueur(s) :  ".replace("&", "§") + Bukkit.getOnlinePlayers().size() ).setScore(6);
			
			obj.getScore("§7--").setScore(5);
			obj.getScore("§7---").setScore(4);
			if(main.isInGame(p)) {
			
				obj.getScore("§7En Combat").setScore(3);

				// A continuer
			}else if(main.isInQueue(p)) {
				obj.getScore("§7En Attente  ").setScore(3);
			}else {
				obj.getScore("§7Au Lobby").setScore(3);
			}
			
			obj.getScore("§7----").setScore(2);
			
			obj.getScore("§8play.yetilounge.fr").setScore(1);
			
			p.setScoreboard(scoreboard);
			
		}
		
		

	}

}
