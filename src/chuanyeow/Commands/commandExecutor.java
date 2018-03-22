package chuanyeow.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import chuanyeow.Final;
import chuanyeow.Game;

public class commandExecutor implements CommandExecutor {
	
	private Final plugin;
	
	public commandExecutor(Final plugin) {
		this.plugin = plugin;
	}
	
	//Commands
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
		//Spawning
        if (command.getName().equalsIgnoreCase("spawn"))
        {
        	Player player = (Player) sender;
        	World SpawnWorld = Bukkit.getServer().getWorld(plugin.getConfig().getString("Spawn.World"));
        	double SpawnX = plugin.getConfig().getDouble("Spawn.x");
        	double SpawnY = plugin.getConfig().getDouble("Spawn.y");
        	double SpawnZ = plugin.getConfig().getDouble("Spawn.z");
        	
            player.teleport(new Location(SpawnWorld, SpawnX, SpawnY, SpawnZ));
            plugin.playersInGame.remove(player.getUniqueId());
            plugin.game.Game01.remove(player.getUniqueId());
            plugin.game.Game02.remove(player.getUniqueId());
            plugin.game.Game03.remove(player.getUniqueId());
            plugin.game.Game04.remove(player.getUniqueId());
            plugin.game.Game05.remove(player.getUniqueId());
            player.getInventory().clear();
            player.performCommand("hubitems");
            return true;
        }
        
        //HubItems
        if (command.getName().equalsIgnoreCase("hubitems"))
        {
        	Player player = (Player) sender;
        	if(player.getWorld() == Bukkit.getWorld(plugin.getConfig().getString("Spawn.World"))){
	        	ItemStack CompassIcon = new ItemStack(Material.COMPASS);
	        	ItemMeta CompassMeta = CompassIcon.getItemMeta();
	        	CompassMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Compass.Name")));
	        	ArrayList<String> CompassLore = new ArrayList<String>();
	        	CompassLore.add(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Compass.Desc")));
	        	CompassMeta.setLore(CompassLore);
	        	CompassIcon.setItemMeta(CompassMeta);
	        	
	        	player.getInventory().clear();
	        	player.getInventory().setItem(0, CompassIcon);
	        	return true;
        	}else {
        		player.sendMessage(ChatColor.RED + "You cannot perform this Command while In-Game!");
        		return true;
        	}
        }
        
        //ServerInfo
        if (command.getName().equalsIgnoreCase("serverinfo")) {
        	Player player = (Player) sender;
        	
        	String serverIF1 = plugin.getConfig().getString("ServerIF.Line 1");
        	String serverIF2 = plugin.getConfig().getString("ServerIF.Line 2");
        	String serverIF3 = plugin.getConfig().getString("ServerIF.Line 3");
        	String serverIF4 = plugin.getConfig().getString("ServerIF.Line 4");
        	String serverIF5 = plugin.getConfig().getString("ServerIF.Line 5");
        	String serverIF6 = plugin.getConfig().getString("ServerIF.Line 6");
        	String serverIF7 = plugin.getConfig().getString("ServerIF.Line 7");
        	String serverIF8 = plugin.getConfig().getString("ServerIF.Line 8");
        	String serverIF9 = plugin.getConfig().getString("ServerIF.Line 9");
        	String serverIF10 = plugin.getConfig().getString("ServerIF.Line 10");

        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF1));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF2));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF3));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF4));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF5));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF6));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF7));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF8));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF9));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverIF10));
        	player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 2, 2);
        	return true;
        }
        
        //Haunt
        if (command.getName().equalsIgnoreCase("haunt")) {
        	Player send = (Player) sender;
            Player player = Bukkit.getPlayer(args[0]);
        	
            if(player == null) {
            	send.sendMessage(ChatColor.RED + "Player does not Exist!");
            }
            else
            {
        	player.sendMessage(ChatColor.RED + "You have been Cursed..");
        	player.sendMessage(ChatColor.RED + "Beware the Sudden Turn");
        	player.getWorld().playSound(player.getLocation(), Sound.RECORD_13, 2, 1);
        	player.getWorld().strikeLightning(player.getLocation());
        	player.getWorld().setThundering(true);
        	player.getWorld().setTime(16000);
        	player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
            }
            return true;
        }
        
        //ServerHelp
        if (command.getName().equalsIgnoreCase("serverhelp")) {
        	Player player = (Player) sender;
        	String serverHP1 = plugin.getConfig().getString("ServerHP.Line 1");
        	String serverHP2 = plugin.getConfig().getString("ServerHP.Line 2");
        	String serverHP3 = plugin.getConfig().getString("ServerHP.Line 3");
        	String serverHP4 = plugin.getConfig().getString("ServerHP.Line 4");
        	String serverHP5 = plugin.getConfig().getString("ServerHP.Line 5");
        	String serverHP6 = plugin.getConfig().getString("ServerHP.Line 6");
        	String serverHP7 = plugin.getConfig().getString("ServerHP.Line 7");
        	String serverHP8 = plugin.getConfig().getString("ServerHP.Line 8");
        	String serverHP9 = plugin.getConfig().getString("ServerHP.Line 9");
        	String serverHP10 = plugin.getConfig().getString("ServerHP.Line 10");

        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP1));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP2));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP3));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP4));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP5));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP6));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP7));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP8));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP9));
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', serverHP10));
        	player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 2, 2);
        	return true;
        }
        
        //NukesLobby
        if (command.getName().equalsIgnoreCase("nukeslobby"))
        {
        	Player player = (Player) sender;
        	if(player.getWorld() == Bukkit.getWorld(plugin.getConfig().getString("Spawn.World"))){
	        	Inventory nukes = Bukkit.createInventory(null, 9, "Select a Nukes MiniGame!");
				
				int players1 = plugin.game.Game01.size();
				int players1max = plugin.getConfig().getInt("ArenaWorlds.Game1Max");
				int players2 = plugin.game.Game02.size();
				int players2max = plugin.getConfig().getInt("ArenaWorlds.Game2Max");
				int players3 = plugin.game.Game03.size();
				int players3max = plugin.getConfig().getInt("ArenaWorlds.Game3Max");
				int players4 = plugin.game.Game04.size();
				int players4max = plugin.getConfig().getInt("ArenaWorlds.Game4Max");
				int players5 = plugin.game.Game05.size();
				int players5max = plugin.getConfig().getInt("ArenaWorlds.Game5Max");
				
				//Item Stacks and Metas
				ItemStack Game1 = new ItemStack(Material.EMERALD_BLOCK);
				ItemStack Game2 = new ItemStack(Material.EMERALD_BLOCK);
				ItemStack Game3 = new ItemStack(Material.EMERALD_BLOCK);
				ItemStack Game4 = new ItemStack(Material.EMERALD_BLOCK);
				ItemStack Game5 = new ItemStack(Material.EMERALD_BLOCK);
				ItemMeta Game1Meta = Game1.getItemMeta();
				ItemMeta Game2Meta = Game2.getItemMeta();
				ItemMeta Game3Meta = Game3.getItemMeta();
				ItemMeta Game4Meta = Game4.getItemMeta();
				ItemMeta Game5Meta = Game5.getItemMeta();
				Game1Meta.setDisplayName(ChatColor.YELLOW + "Game: Nukes 01");
				Game2Meta.setDisplayName(ChatColor.YELLOW + "Game: Nukes 02");
				Game3Meta.setDisplayName(ChatColor.YELLOW + "Game: Nukes 03");
				Game4Meta.setDisplayName(ChatColor.YELLOW + "Game: Nukes 04");
				Game5Meta.setDisplayName(ChatColor.YELLOW + "Game: Nukes 05");
				
				//Item Lore
				ArrayList <String> Game1Lore = new ArrayList<String>();
				ArrayList <String> Game2Lore = new ArrayList<String>();
				ArrayList <String> Game3Lore = new ArrayList<String>();
				ArrayList <String> Game4Lore = new ArrayList<String>();
				ArrayList <String> Game5Lore = new ArrayList<String>();
				Game1Lore.add(ChatColor.GOLD + "Players: " + players1 + "/" + players1max);
				Game1Lore.add(ChatColor.UNDERLINE + "Click to Join Game!");
				Game2Lore.add(ChatColor.GOLD + "Players: " + players2 + "/" + players2max);
				Game2Lore.add(ChatColor.UNDERLINE + "Click to Join Game!");
				Game3Lore.add(ChatColor.GOLD + "Players: " + players3 + "/" + players3max);
				Game3Lore.add(ChatColor.UNDERLINE + "Click to Join Game!");
				Game4Lore.add(ChatColor.GOLD + "Players: " + players4 + "/" + players4max);
				Game4Lore.add(ChatColor.UNDERLINE + "Click to Join Game!");
				Game5Lore.add(ChatColor.GOLD + "Players: " + players5 + "/" + players5max);
				Game5Lore.add(ChatColor.UNDERLINE + "Click to Join Game!");
				Game1Meta.setLore(Game1Lore);
				Game2Meta.setLore(Game2Lore);
				Game3Meta.setLore(Game3Lore);
				Game4Meta.setLore(Game4Lore);
				Game5Meta.setLore(Game5Lore);
	
				Game1.setItemMeta(Game1Meta);
				Game2.setItemMeta(Game2Meta);
				Game3.setItemMeta(Game3Meta);
				Game4.setItemMeta(Game4Meta);
				Game5.setItemMeta(Game5Meta);
				
				//States for Games
				if(players1 <= 0) {
					Game1.setAmount(1);
					Game1.setType(Material.IRON_BLOCK);
				}else if(players1 >= 1) {
					Game1.setAmount(players1);
					Game1.setType(Material.EMERALD_BLOCK);
				}else if(players1 >= players1max) {
					Game1.setType(Material.COAL_BLOCK);
					Game1Lore.set(1, ChatColor.RED + "Game Full!");
				}
				
				if(players2 <= 0) {
					Game2.setAmount(1);
					Game2.setType(Material.IRON_BLOCK);
				}else if(players2 >= 1) {
					Game2.setAmount(players2);
					Game2.setType(Material.EMERALD_BLOCK);
				}else if(players2 >= players2max) {
					Game2.setType(Material.COAL_BLOCK);
					Game2Lore.set(1, ChatColor.RED + "Game Full!");
				}
				
				if(players3 <= 0) {
					Game3.setAmount(1);
					Game3.setType(Material.IRON_BLOCK);
				}else if(players3 >= 1) {
					Game3.setAmount(players3);
					Game3.setType(Material.EMERALD_BLOCK);
				}else if(players3 >= players3max) {
					Game3.setType(Material.COAL_BLOCK);
					Game3Lore.set(1, ChatColor.RED + "Game Full!");
				}
				
				if(players4 <= 0) {
					Game4.setAmount(1);
					Game4.setType(Material.IRON_BLOCK);
				}else if(players4 >= 1) {
					Game4.setAmount(players4);
					Game4.setType(Material.EMERALD_BLOCK);
				}else if(players4 >= players4max) {
					Game4.setType(Material.COAL_BLOCK);
					Game4Lore.set(1, ChatColor.RED + "Game Full!");
				}
				
				if(players5 <= 0) {
					Game5.setAmount(1);
					Game5.setType(Material.IRON_BLOCK);
				}else if(players5 >= 1) {
					Game5.setAmount(players5);
					Game5.setType(Material.EMERALD_BLOCK);
				}else if(players5 >= players5max) {
					Game5.setType(Material.COAL_BLOCK);
					Game5Lore.set(1, ChatColor.RED + "Game Full!");
				}
				
				nukes.addItem(Game1, Game2, Game3, Game4, Game5);
				player.openInventory(nukes);
				return true;
	        }else {
	        	player.sendMessage(ChatColor.RED + "You cannot perform this Command while In-Game!");
	        	return true;
	        }
        }
		return false;
    }
}
