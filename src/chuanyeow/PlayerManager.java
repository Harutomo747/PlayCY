package chuanyeow;

import java.util.UUID;

import org.bukkit.event.Listener;

public class PlayerManager implements Listener {
	
	public UUID uuid;
	private boolean inGame;
	private int arena;
	private boolean isDead;
	
	public PlayerManager(UUID uuid, boolean ingame, int arena, boolean isdead){
		this.setUuid(uuid);
		this.setInGame(ingame);
		this.setArena(arena);
		this.setDead(isdead);
	}
	public PlayerManager() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public int getArena() {
		return arena;
	}

	public void setArena(int arena) {
		this.arena = arena;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
}
