package fr.rorocraft.practice.builduhc;

import java.util.List;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.rorocraft.practice.Main;

public class UhcKit {

	public UhcKit() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public void addKit(Player p, Main main) {
		p.getInventory().clear();
		
		if(main.dataConfig.get("kit." + p.getUniqueId().toString() +".builduhc.items") != null) {
			
			// loadKit(p, main);
			
			
			ItemStack[] items = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".builduhc.items")).toArray(new ItemStack[0]);
			ItemStack[] armor = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".builduhc.armor")).toArray(new ItemStack[0]);
			
			p.getInventory().setContents(items);
			p.getInventory().setArmorContents(armor);
		} else {
			ItemStack epee_diams = new ItemStack(Material.DIAMOND_SWORD);
			epee_diams.addEnchantment(Enchantment.DAMAGE_ALL, 3);
			p.getInventory().addItem(epee_diams);
			
			ItemStack canne_a_peche = new ItemStack(Material.FISHING_ROD);
			p.getInventory().addItem(canne_a_peche);
			
			ItemStack bow = new ItemStack(Material.BOW);
			bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
			p.getInventory().addItem(bow);
			
			ItemStack pierre = new ItemStack(Material.COBBLESTONE, 64);
			p.getInventory().addItem(pierre);
			
			ItemStack lave = new ItemStack(Material.LAVA_BUCKET);
			p.getInventory().addItem(lave);
			
			ItemStack eau = new ItemStack(Material.WATER_BUCKET);
			p.getInventory().addItem(eau);
			
			ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
			p.getInventory().addItem(steak);
			
			ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 9);
			p.getInventory().addItem(apple);
			
			p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
			
			p.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
			
			p.getInventory().addItem(new ItemStack(Material.WOOD, 64));
			
			p.getInventory().addItem(new ItemStack(Material.ARROW, 32));
			
			p.getInventory().addItem(lave);
			
			p.getInventory().addItem(eau);
			
			ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
			helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
			p.getInventory().setHelmet(helmet);
			
			
			ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
			chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			p.getInventory().setChestplate(chestplate);
			
			ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
			leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
			p.getInventory().setLeggings(leggings);
			
			ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
			boots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
			p.getInventory().setBoots(boots);
			
			
		
			
		}
	}


		
}
