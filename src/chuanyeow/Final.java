package chuanyeow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import chuanyeow.Commands.commandExecutor;
import chuanyeow.Events.Events;
import net.md_5.bungee.api.ChatColor;

public class Final extends JavaPlugin {

	private static Final instance;
	public Game game;
	public ArenaManager am;
	
	public HashMap<UUID, PlayerManager> playerManager = new HashMap<UUID, PlayerManager>();
	public ArrayList<UUID> playersInGame = new ArrayList<UUID>();
	public ItemStack nukesKit;
	public ItemStack leaveItem;
	
	
	@Override
	public void onEnable() {
		setInstance(this);
		ConfigFiles();
		LoadCommands();
		EventRegister();
		InstanceClasses();
		MenuItems();
		game.setupGame();
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "PlayCY Plugin has been Disabled!");
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
    public static Final getInstance() {
        return instance;
    }

    private static void setInstance(Final instance) {
        Final.instance = instance;
    }
    
    private void InstanceClasses() {
    	game = new Game();
    	am = new ArenaManager();
    }
    
	public void MenuItems() {
		//Kits Item
		nukesKit = new ItemStack(Material.DIAMOND);
		ItemMeta nukesKitMeta = nukesKit.getItemMeta();
		nukesKitMeta.setDisplayName(ChatColor.RED + "- " + ChatColor.BOLD + "Select a Nukes Kit!");
		ArrayList <String> nukesKitLore = new ArrayList<String>();
		nukesKitLore.add(ChatColor.GREEN + "Choose a Kit for this Game");
		nukesKitMeta.setLore(nukesKitLore);
		nukesKit.setItemMeta(nukesKitMeta);
		
		//Leave Item
		leaveItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta leaveItemMeta = leaveItem.getItemMeta();
		leaveItemMeta.setDisplayName(ChatColor.RED + "- " + ChatColor.BOLD + "Leave the Game");
		ArrayList <String> leaveItemLore = new ArrayList<String>();
		leaveItemLore.add(ChatColor.DARK_AQUA + "Exits to the Main Lobby");
		leaveItemMeta.setLore(leaveItemLore);
		leaveItem.setItemMeta(leaveItemMeta);
	}
}
