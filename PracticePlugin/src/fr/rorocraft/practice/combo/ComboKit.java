package fr.rorocraft.practice.combo;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.rorocraft.practice.Main;

public class ComboKit {

	public ComboKit() {
		
	}
	
	
	public ItemStack makeItem(Material m, int amount) {
		 ItemStack it = new ItemStack(m, amount);
		
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, byte data) {
		 ItemStack it = new ItemStack(m, amount, data);
		
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level) {
		 ItemStack it = new ItemStack(m, amount);
		 it.addEnchantment(enchant1, level);
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level, Enchantment enchant2, int level2 ) {
		 ItemStack it = new ItemStack(m);
		 it.addEnchantment(enchant1, level);
		 it.addEnchantment(enchant2, level2);
		 return it;
	 }
	 
	 public ItemStack makeItem(Material m, int amount, Enchantment enchant1, int level, Enchantment enchant2, int level2 , Enchantment enchant3, int level3 ) {
		 ItemStack it = new ItemStack(m);
		 it.addEnchantment(enchant1, level);
		 it.addEnchantment(enchant2, level2);
		 it.addEnchantment(enchant3, level3);
		 
		 return it;
	 }
	 

	@SuppressWarnings("unchecked")
	public void addKit(Player p, Main main) {
	p.getInventory().clear();
		
		if(main.dataConfig.get("kit." + p.getUniqueId().toString() +".nodebuff.items") != null) {
			
			// loadKit(p, main);

			ItemStack[] items = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".faction.items")).toArray(new ItemStack[0]);
			ItemStack[] armor = ((List<ItemStack>) main.dataConfig.get("kit." + p.getUniqueId().toString() +".faction.armor")).toArray(new ItemStack[0]);
			
			p.getInventory().setContents(items);
			p.getInventory().setArmorContents(armor);
			
			
        } else {
        	
        	p.getInventory().setItem(0, makeItem(Material.DIAMOND_SWORD, 1, Enchantment.DAMAGE_ALL, 5, Enchantment.DURABILITY, 3, Enchantment.FIRE_ASPECT, 2));
        	
        	p.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
        	
        	ItemStack casque = makeItem(Material.DIAMOND_HELMET, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        	
        	ItemStack chestplate = makeItem(Material.DIAMOND_CHESTPLATE, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        	
        	ItemStack leggings = makeItem(Material.DIAMOND_LEGGINGS, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        	
        	ItemStack boots = makeItem(Material.DIAMOND_BOOTS, 1, Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        	
        	p.getInventory().setHelmet(casque);
        	
        	p.getInventory().setChestplate(chestplate);
        	
        	p.getInventory().setLeggings(leggings);
        	
        	p.getInventory().setBoots(boots);
        	
        	p.getInventory().setItem(2, casque);
        	
        	p.getInventory().setItem(3, boots);
        	
        	p.getInventory().setItem(4,leggings);
        	
        	p.getInventory().setItem(5,  chestplate);
        	
        	
        	p.updateInventory();
        }
		
	}

}
