package chuanyeow;

import org.bukkit.event.Listener;

public class ArenaManager implements Listener{
	
	private int ArenaID;
	private String ArenaWorld;
	private int PlayersInGame;
	private double SpawnX;
	private double SpawnY;
	private double SpawnZ;
	
	public ArenaManager(int arenaID, String arenaWorld, int playersInGame, double spawnX, double spawnY, double spawnZ) {
		this.setArenaID(arenaID);
		this.setArenaWorld(arenaWorld);
		this.setPlayersInGame(playersInGame);
		this.setSpawnX(spawnX);
		this.setSpawnY(spawnY);
		this.setSpawnZ(spawnZ);
	}
	public ArenaManager() {}

	public int getArenaID() {
		return ArenaID;
	}

	public void setArenaID(int arenaID) {
		ArenaID = arenaID;
	}

	public String getArenaWorld() {
		return ArenaWorld;
	}

	public void setArenaWorld(String arenaWorld) {
		ArenaWorld = arenaWorld;
	}

	public int getPlayersInGame() {
		return PlayersInGame;
	}

	public void setPlayersInGame(int playersInGame) {
		PlayersInGame = playersInGame;
	}

	public double getSpawnX() {
		return SpawnX;
	}

	public void setSpawnX(double spawnX) {
		SpawnX = spawnX;
	}

	public double getSpawnY() {
		return SpawnY;
	}

	public void setSpawnY(double spawnY) {
		SpawnY = spawnY;
	}

	public double getSpawnZ() {
		return SpawnZ;
	}

	public void setSpawnZ(double spawnZ) {
		SpawnZ = spawnZ;
	}

}
