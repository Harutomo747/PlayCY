package chuanyeow.Events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import chuanyeow.Final;
import chuanyeow.Game;
import chuanyeow.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {
	
	private Final plugin = Final.getPlugin(Final.class);
	
	public Events(Final finalio) {
	}

	@EventHandler
	public void joinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		
		//Actual Event
		player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 2, 1);
		player.performCommand("hubitems");
		player.performCommand("spawn");
		player.sendMessage(ChatColor.LIGHT_PURPLE + "=+=----------------[PlayCY]----------------=+=");
		player.sendMessage(ChatColor.GOLD + "Welcome to PlayCY!");
		player.sendMessage(" ");
		player.sendMessage(ChatColor.RED + "[SERVER NEWS]");
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 1")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 2")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 3")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 4")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 5")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("News.Line 6")));
		plugin.playersInGame.remove(uuid);
	}
	
	@EventHandler
	public void leaveEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		
		plugin.playersInGame.remove(uuid);
	}
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (event.getItemDrop().getItemStack().getType() == Material.COMPASS && player.getWorld() == Bukkit.getWorld(plugin.getConfig().getString("Spawn.World"))){
			event.setCancelled(true);
		}
		if (event.getItemDrop().getItemStack().equals(plugin.nukesKit)) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		//Compass Menu
		Player player = event.getPlayer();
		if(event.getItem().getType() == Material.COMPASS && player.getWorld() == Bukkit.getWorld(plugin.getConfig().getString("Spawn.World"))) {
			player.performCommand("nukeslobby");
		}
		
		//Kits Menu
		if(event.getItem().getType() == Material.DIAMOND && event.getItem().isSimilar(plugin.nukesKit)) {
			Inventory nukeskit = Bukkit.createInventory(null, 9, "Select a Nukes Kit!");
			ItemStack blocksmith = new ItemStack(Material.GRASS);
			ItemStack timber = new ItemStack(Material.IRON_AXE);
			ItemStack shoveller = new ItemStack(Material.IRON_SPADE);
			ItemStack miner = new ItemStack(Material.IRON_PICKAXE);
			ItemStack shelter = new ItemStack(Material.CHAINMAIL_HELMET);
			ItemMeta blocksmithMeta = blocksmith.getItemMeta();
			ItemMeta timberMeta = timber.getItemMeta();
			ItemMeta shovellerMeta = shoveller.getItemMeta();
			ItemMeta minerMeta = miner.getItemMeta();
			ItemMeta shelterMeta = shelter.getItemMeta();
			blocksmithMeta.setDisplayName(ChatColor.GOLD + "Kit: BlockSmith");
			timberMeta.setDisplayName(ChatColor.GOLD + "Kit: Timber");
			shovellerMeta.setDisplayName(ChatColor.GOLD + "Kit: Shoveller");
			minerMeta.setDisplayName(ChatColor.GOLD + "Kit: Miner");
			shelterMeta.setDisplayName(ChatColor.GOLD + "Kit: Shelter");
			ArrayList <String> blocksmithLore = new ArrayList<String>();
			ArrayList <String> timberLore = new ArrayList<String>();
			ArrayList <String> shovellerLore = new ArrayList<String>();
			ArrayList <String> minerLore = new ArrayList<String>();
			ArrayList <String> shelterLore = new ArrayList<String>();
			blocksmithLore.add(ChatColor.YELLOW + "Uses a Range of Blocks for");
			blocksmithLore.add(ChatColor.YELLOW + "Faster Building.");
			timberLore.add(ChatColor.YELLOW + "Faster way of Obtaining Wood");
			timberLore.add(ChatColor.YELLOW + "before Nukes Begin.");
			shovellerLore.add(ChatColor.YELLOW + "Quick & Dirty way of Burying,");
			shovellerLore.add(ChatColor.YELLOW + "or Digging up Ancient Treasure.");
			minerLore.add(ChatColor.YELLOW + "Maybe find Diamonds? Not like I have");
			minerLore.add(ChatColor.YELLOW + "Nukes to worry about, Right?");
			shelterLore.add(ChatColor.YELLOW + "Easy to break and place, a");
			shelterLore.add(ChatColor.YELLOW + "Perfect Decoy! " + ChatColor.DARK_PURPLE + "amirite");
			blocksmithMeta.setLore(blocksmithLore);
			timberMeta.setLore(timberLore);
			shovellerMeta.setLore(shovellerLore);
			minerMeta.setLore(minerLore);
			shelterMeta.setLore(shelterLore);
			blocksmith.setItemMeta(blocksmithMeta);
			timber.setItemMeta(timberMeta);
			shoveller.setItemMeta(shovellerMeta);
			miner.setItemMeta(minerMeta);
			shelter.setItemMeta(shelterMeta);
			nukeskit.setItem(2, blocksmith);
			nukeskit.setItem(3, timber);
			nukeskit.setItem(4, shoveller);
			nukeskit.setItem(5, miner);
			nukeskit.setItem(6, timber);
			nukeskit.setItem(7, shelter);
			
			player.openInventory(nukeskit);
		}
		if(event.getItem().getType() == Material.REDSTONE_BLOCK && event.getItem().isSimilar(plugin.leaveItem)) {
			player.getInventory().clear();
			player.performCommand("spawn");
			player.sendMessage(ChatColor.RED + "You have Left the Game!");
		}
	}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		UUID uuid = player.getUniqueId();
    	
    	//Full Game
		if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
			if(event.getCurrentItem().getType() == Material.COAL_BLOCK) {
				player.sendMessage(ChatColor.RED + "Game is Full!");
				player.closeInventory();
			}
		}
			
    	//Joining Nukes Arena 1
		if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
			if(event.getCurrentItem().getType() == Material.IRON_BLOCK || event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
				if(event.getSlot() == 0) {
					if(event.getClickedInventory().getItem(0).getAmount() <= plugin.getConfig().getInt("ArenaWorlds.Game1Max")) {
						plugin.playerManager.put(uuid, new PlayerManager(uuid, false, 1, false));
						plugin.playersInGame.add(uuid);
						player.setTotalExperience(1);
						plugin.game.playerJoin(player);
						plugin.game.Game01.add(uuid);
						player.getInventory().clear();
						player.getInventory().setItem(0, plugin.nukesKit);
						player.getInventory().setItem(8, plugin.leaveItem);
						player.closeInventory();
					}else {
						plugin.playersInGame.remove(uuid);
						player.sendMessage(ChatColor.RED + "Game is Full!");
						player.closeInventory();
					}
				}
			}
		}
	    	//Joining Nukes Arena 2
			if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
				if(event.getCurrentItem().getType() == Material.IRON_BLOCK || event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
					if(event.getSlot() == 1) {
						if(event.getClickedInventory().getItem(1).getAmount() <= plugin.getConfig().getInt("ArenaWorlds.Game2Max")) {
							plugin.playerManager.put(uuid, new PlayerManager(uuid, false, 2, false));
							plugin.playersInGame.add(uuid);
							player.setTotalExperience(2);
							plugin.game.playerJoin(player);
							plugin.game.Game02.add(uuid);
							player.getInventory().clear();
							player.getInventory().setItem(0, plugin.nukesKit);
							player.getInventory().setItem(8, plugin.leaveItem);
							player.closeInventory();
						}else {
							plugin.playersInGame.remove(uuid);
							player.sendMessage(ChatColor.RED + "Game is Full!");
							player.closeInventory();
						}
					}
				}
			}
    	//Joining Nukes Arena 3
		if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
			if(event.getCurrentItem().getType() == Material.IRON_BLOCK || event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
				if(event.getSlot() == 2) {
					if(event.getClickedInventory().getItem(2).getAmount() <= plugin.getConfig().getInt("ArenaWorlds.Game3Max")) {
						plugin.playerManager.put(uuid, new PlayerManager(uuid, false, 3, false));
						plugin.playersInGame.add(uuid);
						player.setTotalExperience(3);
						plugin.game.playerJoin(player);
						plugin.game.Game03.add(uuid);
						player.getInventory().clear();
						player.getInventory().setItem(0, plugin.nukesKit);
						player.getInventory().setItem(8, plugin.leaveItem);
						player.closeInventory();
					}else {
						plugin.playersInGame.remove(uuid);
						player.sendMessage(ChatColor.RED + "Game is Full!");
						player.closeInventory();
					}
				}
			}
		}
	    	//Joining Nukes Arena 4
			if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
				if(event.getCurrentItem().getType() == Material.IRON_BLOCK || event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
					if(event.getSlot() == 3) {
						if(event.getClickedInventory().getItem(3).getAmount() <= plugin.getConfig().getInt("ArenaWorlds.Game4Max")) {
							plugin.playerManager.put(uuid, new PlayerManager(uuid, false, 4, false));
							plugin.playersInGame.add(uuid);
							player.setTotalExperience(4);
							plugin.game.playerJoin(player);
							plugin.game.Game04.add(uuid);
							player.getInventory().clear();
							player.getInventory().setItem(0, plugin.nukesKit);
							player.getInventory().setItem(8, plugin.leaveItem);
							player.closeInventory();
						}else {
							plugin.playersInGame.remove(uuid);
							player.sendMessage(ChatColor.RED + "Game is Full!");
							player.closeInventory();
						}
					}
				}
			}
		    	//Joining Nukes Arena 5
				if(event.getClickedInventory().getTitle() == "Select a Nukes MiniGame!") {
					if(event.getCurrentItem().getType() == Material.IRON_BLOCK || event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
						if(event.getSlot() == 4) {
							if(event.getClickedInventory().getItem(4).getAmount() <= plugin.getConfig().getInt("ArenaWorlds.Game5Max")) {
								plugin.playerManager.put(uuid, new PlayerManager(uuid, false, 5, false));
								plugin.playersInGame.add(uuid);
								player.setTotalExperience(5);
								plugin.game.playerJoin(player);
								plugin.game.Game05.add(uuid);
								player.getInventory().clear();
								player.getInventory().setItem(0, plugin.nukesKit);
								player.getInventory().setItem(8, plugin.leaveItem);
								player.closeInventory();
							}else {
								plugin.playersInGame.remove(uuid);
								player.sendMessage(ChatColor.RED + "Game is Full!");
								player.closeInventory();
							}
						}
					}
				}
				/* nukeskit.setItem(6, timber);
				nukeskit.setItem(7, shelter); */
				//Nukes Kits
				if(event.getClickedInventory().getTitle() == "Select a Nukes Kit!") {
					if(event.getSlot() == 2) {
						event.setCancelled(true);
						ItemStack Item1 = new ItemStack(Material.GRASS);
						ItemStack Item2 = new ItemStack(Material.RED_SANDSTONE);
						ItemStack Item3 = new ItemStack(Material.SANDSTONE);
						ItemStack Item4 = new ItemStack(Material.FURNACE);
						ItemStack Item5 = new ItemStack(Material.LOG);
						Item1.setAmount(25);
						Item2.setAmount(25);
						Item3.setAmount(25);
						Item4.setAmount(4);
						Item5.setAmount(5);
						player.getEnderChest().clear();
						player.getEnderChest().addItem(Item1, Item2, Item3, Item4, Item5);
						player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 2, 2);
						player.sendMessage(ChatColor.BLUE + "You have Selected the " + ChatColor.YELLOW + "BlockSmith " + ChatColor.BLUE + "Kit!");
						player.closeInventory();
					}
					
					if(event.getSlot() == 3) {
						event.setCancelled(true);
						ItemStack Item1 = new ItemStack(Material.STONE_AXE);
						ItemStack Item2 = new ItemStack(Material.BONE);
						ItemStack Item3 = new ItemStack(Material.SAPLING);
						ItemStack Item4 = new ItemStack(Material.FURNACE);
						Item1.setAmount(1);
						Item2.setAmount(3);
						Item3.setAmount(5);
						Item4.setAmount(4);
						
						player.getEnderChest().clear();
						player.getEnderChest().addItem(Item1, Item2, Item3, Item4);
						player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 2, 2);
						player.sendMessage(ChatColor.BLUE + "You have Selected the " + ChatColor.YELLOW + "Timber " + ChatColor.BLUE + "Kit!");
						player.closeInventory();
					}
					if(event.getSlot() == 4) {
						event.setCancelled(true);
						ItemStack Item1 = new ItemStack(Material.IRON_SPADE);
						ItemStack Item2 = new ItemStack(Material.GOLD_BLOCK);
						ItemStack Item3 = new ItemStack(Material.TORCH);
						ItemStack Item4 = new ItemStack(Material.FURNACE);
						Item1.setAmount(1);
						Item2.setAmount(2);
						Item3.setAmount(35);
						Item4.setAmount(4);
						
						player.getEnderChest().clear();
						player.getEnderChest().addItem(Item1, Item2, Item3, Item4);
						player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 2, 2);
						player.sendMessage(ChatColor.BLUE + "You have Selected the " + ChatColor.YELLOW + "Shoveller " + ChatColor.BLUE + "Kit!");
						player.closeInventory();
					}
					if(event.getSlot() == 5) {
						event.setCancelled(true);
						ItemStack Item1 = new ItemStack(Material.STONE_PICKAXE);
						ItemStack Item2 = new ItemStack(Material.TORCH);
						ItemStack Item3 = new ItemStack(Material.GLASS);
						ItemStack Item4 = new ItemStack(Material.FURNACE);
						Item1.setAmount(1);
						Item2.setAmount(35);
						Item3.setAmount(35);
						Item4.setAmount(4);
						
						player.getEnderChest().clear();
						player.getEnderChest().addItem(Item1, Item2, Item3, Item4);
						player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 2, 2);
						player.sendMessage(ChatColor.BLUE + "You have Selected the " + ChatColor.YELLOW + "Miner " + ChatColor.BLUE + "Kit!");
						player.closeInventory();
					}
				}
	}
}