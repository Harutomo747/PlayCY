package chuanyeow;
import org.bukkit.plugin.java.JavaPlugin;

import chuanyeow.Commands.commandExecutor;
import chuanyeow.Events.Events;
import net.md_5.bungee.api.ChatColor;

public class Final extends JavaPlugin {
	
	@Override
	public void onEnable() {
		ConfigFiles();
		LoadCommands();
		EventRegister();
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
}
