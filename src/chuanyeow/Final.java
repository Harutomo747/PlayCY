package chuanyeow;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import chuanyeow.Commands.commandExecutor;
import chuanyeow.Events.Events;
import net.md_5.bungee.api.ChatColor;

public class Final extends JavaPlugin {
	
	public static Inventory nukes = Bukkit.createInventory(null, 9, "Select a Nukes MiniGame!");
	
	@Override
	public void onEnable() {
		ConfigFiles();
		LoadCommands();
		EventRegister();
		InitializeInventories();
	}
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "PlayCY Disabled!");
	}
	
	public void ConfigFiles() {
		//Load Config Files
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void LoadCommands() {
		//Load Commands
		getCommand("spawn").setExecutor(new commandExecutor(this));
		getCommand("hubitems").setExecutor(new commandExecutor(this));
		getCommand("haunt").setExecutor(new commandExecutor(this));
		getCommand("serverinfo").setExecutor(new commandExecutor(this));
		getCommand("serverhelp").setExecutor(new commandExecutor(this));
		getCommand("nukeslobby").setExecutor(new commandExecutor(this));
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Commands Registered!");
	}

	public void EventRegister() {
		//Event Registration
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Events Registered!");
	}
	
	public void InitializeInventories() {
			//Array to Appeal to Type and only able to Add
			ArrayList<ItemStack> id = new ArrayList<ItemStack>();
			ArrayList<ItemMeta> idmeta = new ArrayList<ItemMeta>();

			ItemStack NukesGame01 = new ItemStack(Material.EMERALD_BLOCK);
			ItemMeta GameMeta01 = NukesGame01.getItemMeta();
			
			//Looping Nukes Arenas 10 Times
			for(int i=0; i<9; i++) {
				id.add(NukesGame01);
				idmeta.add(GameMeta01);
				GameMeta01.setDisplayName(ChatColor.LIGHT_PURPLE + "Nukes Game " + i);
				NukesGame01.setItemMeta(GameMeta01);
				nukes.setItem(i, NukesGame01);
				
	/*			if(worldPlayers01 < 9) {
					worldPlayers01++;
				}	*/
				
				getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Nukes Arena " + i + " is Initialized!");
			}
			getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "All Nukes Arenas have been Initialized!");
	}
}