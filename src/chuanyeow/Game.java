package chuanyeow;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.entity.Player;

public class Game implements Listener {
	
	private Final plugin = Final.getPlugin(Final.class);
	public int WaitLobbyCountdown = 120;
	public boolean startCountdown;
	public boolean isStarted;
	public String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix").toString());
	
	//Waiting Lobby
	World WL = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.WaitingLobby"));
	double WLX = plugin.getConfig().getDouble("ArenaWorlds.WaitingLobbyx");
	double WLY = plugin.getConfig().getDouble("ArenaWorlds.WaitingLobbyy");
	double WLZ = plugin.getConfig().getDouble("ArenaWorlds.WaitingLobbyz");
	//Games Arenas 01
	World Game1 = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.Game1"));
	double Game1X = plugin.getConfig().getDouble("ArenaWorlds.Game1x");
	double Game1Y = plugin.getConfig().getDouble("ArenaWorlds.Game1y");
	double Game1Z = plugin.getConfig().getDouble("ArenaWorlds.Game1z");
	//Games Arenas 02
	World Game2 = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.Game2"));
	double Game2X = plugin.getConfig().getDouble("ArenaWorlds.Game2x");
	double Game2Y = plugin.getConfig().getDouble("ArenaWorlds.Game2y");
	double Game2Z = plugin.getConfig().getDouble("ArenaWorlds.Game2z");
	//Games Arenas 03
	World Game3 = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.Game3"));
	double Game3X = plugin.getConfig().getDouble("ArenaWorlds.Game3x");
	double Game3Y = plugin.getConfig().getDouble("ArenaWorlds.Game3y");
	double Game3Z = plugin.getConfig().getDouble("ArenaWorlds.Game3z");
	//Games Arenas 04
	World Game4 = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.Game4"));
	double Game4X = plugin.getConfig().getDouble("ArenaWorlds.Game4x");
	double Game4Y = plugin.getConfig().getDouble("ArenaWorlds.Game4y");
	double Game4Z = plugin.getConfig().getDouble("ArenaWorlds.Game4z");
	//Games Arenas 05
	World Game5 = Bukkit.getServer().getWorld(plugin.getConfig().getString("ArenaWorlds.Game5"));
	double Game5X = plugin.getConfig().getDouble("ArenaWorlds.Game5x");
	double Game5Y = plugin.getConfig().getDouble("ArenaWorlds.Game5y");
	double Game5Z = plugin.getConfig().getDouble("ArenaWorlds.Game5z");
	
	//Games Array Lists
	public ArrayList<UUID> Game01 = new ArrayList<UUID>();
	public ArrayList<UUID> Game02 = new ArrayList<UUID>();
	public ArrayList<UUID> Game03 = new ArrayList<UUID>();
	public ArrayList<UUID> Game04 = new ArrayList<UUID>();
	public ArrayList<UUID> Game05 = new ArrayList<UUID>();
	
	public void setupGame() {
		  playerCheck(Bukkit.getOnlinePlayers().size());
		  for (Player player : Bukkit.getOnlinePlayers()) {
			  if(plugin.playersInGame.contains(player)){
				  	playerJoin(player);
			  }
		  }
	}
	
	public void playerJoin(Player player) {
		int inGame = plugin.playersInGame.size();
		player.teleport(new Location(WL, WLX, WLY, WLZ));
		
		if(Game01.contains(player.getUniqueId())) {
			player.sendMessage(prefix + ChatColor.GOLD + " has Joined the Arena! " + ChatColor.BLUE + "(" + inGame + "/" + plugin.getConfig().getInt("ArenaWorlds.Game1Max") + " Players)");
		}
		else if(Game02.contains(player.getUniqueId())) {
			player.sendMessage(prefix + ChatColor.GOLD + " has Joined the Arena! " + ChatColor.BLUE + "(" + inGame + "/" + plugin.getConfig().getInt("ArenaWorlds.Game2Max") + " Players)");
		}
		else if(Game03.contains(player.getUniqueId())) {
			player.sendMessage(prefix + ChatColor.GOLD + " has Joined the Arena! " + ChatColor.BLUE + "(" + inGame + "/" + plugin.getConfig().getInt("ArenaWorlds.Game3Max") + " Players)");
		}
		else if(Game04.contains(player.getUniqueId())) {
			player.sendMessage(prefix + ChatColor.GOLD + " has Joined the Arena! " + ChatColor.BLUE + "(" + inGame + "/" + plugin.getConfig().getInt("ArenaWorlds.Game4Max") + " Players)");
		}
		else if(Game05.contains(player.getUniqueId())) {
			player.sendMessage(prefix + ChatColor.GOLD + " has Joined the Arena! " + ChatColor.BLUE + "(" + inGame + "/" + plugin.getConfig().getInt("ArenaWorlds.Game5Max") + " Players)");
		}
		
		player.sendMessage(prefix + ChatColor.BLUE + " The Arena will Start in " + WaitLobbyCountdown + " seconds!");
		player.getInventory().clear();
		//Only runs PlayerCheck when player joins ArrayList
		playerCheck(inGame);
	}
	
	public boolean playerCheck(int inGame) {
		if(inGame <= 0) {
			setStarted(false);
			WaitLobbyCountdown = 120;
			lobbyCountdown();
		}
		if(inGame >= 1) {
			setStarted(true);
			lobbyCountdown();
		}
		return isStarted;
	}
	
	public void startGame(Player inGame) {
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		inGame.closeInventory();
		inGame.getInventory().clear();
		inGame.getInventory().setHelmet(helmet);
		inGame.getInventory().setChestplate(chest);
		inGame.getInventory().setLeggings(legs);
		inGame.getInventory().setBoots(boots);
		inGame.getInventory().getHelmet();
		inGame.getInventory().getChestplate();
		inGame.getInventory().getLeggings();
		inGame.getInventory().getBoots();
		
		if(Game01.contains(inGame.getUniqueId())) {
			inGame.teleport(new Location(Game1, Game1X, Game1Y, Game1Z));
			inGame.setTotalExperience(0);
			inGame.getInventory().setContents(inGame.getEnderChest().getContents());
			inGame.playSound(inGame.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
		}
		else if(Game02.contains(inGame.getUniqueId())) {
			inGame.teleport(new Location(Game2, Game2X, Game2Y, Game2Z));
			inGame.setTotalExperience(0);
			inGame.getInventory().addItem(inGame.getEnderChest().getContents());
			inGame.playSound(inGame.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
		}
		else if(Game03.contains(inGame.getUniqueId())) {
			inGame.teleport(new Location(Game3, Game3X, Game3Y, Game3Z));
			inGame.setTotalExperience(0);
			inGame.getInventory().addItem(inGame.getEnderChest().getContents());
			inGame.playSound(inGame.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
		}
		else if(Game04.contains(inGame.getUniqueId())) {
			inGame.teleport(new Location(Game4, Game4X, Game4Y, Game4Z));
			inGame.setTotalExperience(0);
			inGame.getInventory().addItem(inGame.getEnderChest().getContents());
			inGame.playSound(inGame.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
		}
		else if(Game05.contains(inGame.getUniqueId())) {
			inGame.teleport(new Location(Game5, Game5X, Game5Y, Game5Z));
			inGame.setTotalExperience(0);
			inGame.getInventory().addItem(inGame.getEnderChest().getContents());
			inGame.playSound(inGame.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
		}
	}
	
	public void lobbyCountdown() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(plugin.playersInGame.contains(player.getUniqueId()) && (isStarted == true)){
							for(Player inLobby : Bukkit.getOnlinePlayers()) {
								if(WaitLobbyCountdown != -1 && WaitLobbyCountdown != 0) {
										if(isStarted == true) {
											WaitLobbyCountdown--;
										}else {
											WaitLobbyCountdown = 120;
											this.cancel();
									}
							   }else{
									startGame(inLobby);
									plugin.playersInGame.remove(inLobby.getUniqueId());
									setStarted(false);
									this.cancel();
									}
								if(WaitLobbyCountdown == 150) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 3 Minutes to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 2, 2);
								}
								if(WaitLobbyCountdown == 110) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 2 Minutes to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 2, 2);
								}
								if(WaitLobbyCountdown == 62) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 1 Minute to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 2, 1);
								}
								if(WaitLobbyCountdown == 50) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 50 Seconds to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
								}
								if(WaitLobbyCountdown == 40) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 40 Seconds to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
								}
								if(WaitLobbyCountdown == 30) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 30 Seconds to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
								}
								if(WaitLobbyCountdown == 20) {
									inLobby.sendMessage(prefix + ChatColor.BLUE + " 20 Seconds to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
								}
								if(WaitLobbyCountdown <= 10) {
									inLobby.sendMessage(prefix + " " + ChatColor.BLUE + WaitLobbyCountdown + " Seconds to Game Start!");
									inLobby.playSound(inLobby.getLocation(), Sound.BLOCK_ANVIL_BREAK, 2, 2);
								}
							}
						}
					}
			}
		}.runTaskTimer(plugin, 0L, 200L);
	}
	
	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
}