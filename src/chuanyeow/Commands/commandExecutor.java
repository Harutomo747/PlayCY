package chuanyeow.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.onarandombox.MultiverseCore.api.Teleporter;

import chuanyeow.Final;

public class commandExecutor implements CommandExecutor {
	
	private Final plugin;
	
	public commandExecutor(Final plugin) {
		this.plugin = plugin;
	}
	
	//Commands
	@SuppressWarnings("static-access")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
		//Spawn
        if (command.getName().equalsIgnoreCase("spawn"))
        {
        	Player player = (Player) sender;
       // 	World SpawnWorld = Bukkit.getServer().plugin.getConfig().getString("Spawn.World");
        	double SpawnX = plugin.getConfig().getDouble("Spawn.x");
        	double SpawnY = plugin.getConfig().getDouble("Spawn.y");
        	double SpawnZ = plugin.getConfig().getDouble("Spawn.z");
        	
            player.teleport(new Location(Bukkit.getWorld("PlayCY_Main"), SpawnX, SpawnY, SpawnZ));
            return true;
        }
        
        //HubItems
        if (command.getName().equalsIgnoreCase("hubitems"))
        {
        	//Passing Player to Sender as its not Defined, followed by getting player Inventory
        	Player player = (Player) sender;
        	
        	//Get Inventory for ItemStack
        	PlayerInventory inventory = player.getInventory();
        	
        	//ItemStacks (Define Items)
        	ItemStack compass = new ItemStack(Material.COMPASS);
        	ItemStack hideplayers = new ItemStack(Material.EMERALD_BLOCK);
        	
        	//ItemMetas (For Naming Items)
        	ItemMeta compassMeta = compass.getItemMeta();
        	ItemMeta hideplayersMeta = hideplayers.getItemMeta();
        	
        	//Naming Metas
        	compassMeta.setDisplayName(ChatColor.YELLOW + plugin.getConfig().getString("Compass.Name"));
        	hideplayersMeta.setDisplayName(ChatColor.YELLOW + plugin.getConfig().getString("HidePlayers.Name"));
        	compass.setItemMeta(compassMeta);
        	hideplayers.setItemMeta(hideplayersMeta);

        	//Adding Items to Inventory
        	inventory.clear();
        	inventory.setItem(0, compass);
        	inventory.setItem(1, hideplayers);
        	sender.sendMessage("Here are your Hub Items!");
        	return true;
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
        	//Open Inventory
        	player.openInventory(Final.nukes);
        	return true;
        }
        return false;
    }
}
