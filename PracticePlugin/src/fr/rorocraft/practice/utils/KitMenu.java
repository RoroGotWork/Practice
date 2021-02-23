package fr.rorocraft.practice.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import fr.rorocraft.practice.Main;

public class KitMenu {
	
	public void addKit(Player p) {
		p.getInventory().clear();
		
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		
		ItemStack epee = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta epeeMeta = epee.getItemMeta();
		epeeMeta.setDisplayName(ItemName.EPEE_JOIN);
		epee.setItemMeta(epeeMeta);
		
		ItemStack nametag = new ItemStack(Material.NAME_TAG);
		ItemMeta nametagMeta = nametag.getItemMeta();
		nametagMeta.setDisplayName(ItemName.EDIT_KIT);
		nametag.setItemMeta(nametagMeta);
		
		
		
		p.getInventory().setItem(2, epee);
		p.getInventory().setItem(7, nametag);
	}

	
	
	
	
	@SuppressWarnings("deprecation")
	public void addMenuDuel(Player p, Main main, String name) {
		Inventory inv = Bukkit.createInventory(null, 27, name);
		
		ItemStack pomme = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta pommeMeta = pomme.getItemMeta();
		pommeMeta.setDisplayName(ItemName.BUILD_UHC);
		pomme.setItemMeta(pommeMeta);
		if(main.getBuildUhcQueue().size() >= 1) {
			pomme.setAmount(main.getBuildUhcQueue().size());
		} else if(main.getBuildUhcDuel().size() >= 64) {
			pomme.setAmount(64);
		}
		inv.setItem(9, pomme);
		
		
		ItemStack potion = new ItemStack(Material.POTION);
		ItemMeta potionMeta = potion.getItemMeta();
		potionMeta.setDisplayName(ItemName.NO_DEBUFF);
		potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		potion.setItemMeta(potionMeta);
		inv.setItem(11, potion);
		
		ItemStack epee = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta epeeMeta = epee.getItemMeta();
		epeeMeta.setDisplayName(ItemName.FACTION);
		epeeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		epee.setItemMeta(epeeMeta);
		inv.setItem(13, epee);
		
		ItemStack plastron = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta plastronMeta = plastron.getItemMeta();
		plastronMeta.setDisplayName(ItemName.COMBO);
		plastronMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		plastron.setItemMeta(plastronMeta);
		inv.setItem(15, plastron);
		
		ItemStack laisse = new ItemStack(Material.LEASH);
		ItemMeta laisseMeta = laisse.getItemMeta();
		laisseMeta.setDisplayName(ItemName.SUMO);
		laisse.setItemMeta(laisseMeta);
		inv.setItem(17, laisse);
		
		ItemStack white_glass = new ItemStack(Material.STAINED_GLASS_PANE);
		white_glass.setData(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 0));
		inv.setItem(10, white_glass);
		inv.setItem(12, white_glass);
		inv.setItem(14, white_glass);
		inv.setItem(16, white_glass);
		
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack blue_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
			
			if(inv.getItem(i)== null) {
				inv.setItem(i, blue_glass);
			}
		}
		p.openInventory(inv);
	}
	
	
	public void addMenuEdit(Player p) {
		Inventory inv =Bukkit.createInventory(null, 9, MenuName.EDIT_MODE);
		
		
		ItemStack pomme = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta pommeMeta = pomme.getItemMeta();
		pommeMeta.setDisplayName(ItemName.BUILD_UHC);
		pomme.setItemMeta(pommeMeta);
		inv.setItem(1, pomme);
		
		
		ItemStack potion = new ItemStack(Material.POTION, 1, (byte) 16389);
		ItemMeta potionMeta = potion.getItemMeta();
		potionMeta.setDisplayName(ItemName.NO_DEBUFF);
		potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		potion.setItemMeta(potionMeta);
		inv.setItem(4, potion);
		
		
		ItemStack epee = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta epeeMeta = epee.getItemMeta();
		epeeMeta.setDisplayName(ItemName.FACTION);
		epeeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		epee.setItemMeta(epeeMeta);
		inv.setItem(7, epee);
		
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack blue_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 11);
			
			if(inv.getItem(i)== null) {
				inv.setItem(i, blue_glass);
			}
	}
		
		p.openInventory(inv);
	
}
}