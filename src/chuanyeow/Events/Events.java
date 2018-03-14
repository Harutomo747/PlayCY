package chuanyeow.Events;

import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import chuanyeow.Final;
import chuanyeow.Commands.commandExecutor;
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
		
		//Actual Events
		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
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
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent event) {
		Item item = event.getItemDrop();
				
		if (item.getName() == plugin.getConfig().getString("Compass.Name") ||
				(item.getName() == plugin.getConfig().getString("HidePlayers.Name"))) {
			event.setCancelled(true);
		}
	}

}
