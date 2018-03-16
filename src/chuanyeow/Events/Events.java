package chuanyeow.Events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import chuanyeow.Final;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {
	
	//Passing Main Class into Events Class (For Config to Read from Main)
	private Final plugin;
	
	public Events(Final pl) {
		//Naming it just to be Used
		plugin = pl;
	}
	
	@EventHandler
	public void joinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
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
	}
	
/*	@EventHandler(priority = EventPriority.HIGHEST)
	public void dropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getContents() == menuItems().getContents()) {
			event.setCancelled(true);
		}
	}	*/
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerUse(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS && player.getWorld().getName() == "PlayCY_Main") {
			player.performCommand("nukeslobby");
		}
	}

}
