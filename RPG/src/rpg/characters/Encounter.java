package rpg.characters;

import java.util.ArrayList;
import java.util.List;

public class Encounter {
	
	private List<Player> encPlayers =  new ArrayList<Player>();
	private List<Enemy> encEnemies = new ArrayList<Enemy>();
	private List<Entity> turnOrder = new ArrayList<Entity>();
	
	//Contructor
	public Encounter() {
		
	}
	public Encounter(List<Enemy> encEnemies) {
		this.setEncEnemies(encEnemies);
	}
	
	//Getters
	public List<Player> getEncPlayers(){
		return this.encPlayers;
	}
	public List<Enemy> getEncEnemies(){
		return this.encEnemies;
	}
	public List<Entity> getTurnOrder(){
		return this.turnOrder;
	}

	
	//Setters
	public void setEncPlayers(List<Player> encPlayers) {
		if(encPlayers.size() != 0) this.encPlayers = encPlayers;
	}
	public void setEncEnemies(List<Enemy> encEnemies) {
		if(encEnemies.size() != 0) this.encEnemies = encEnemies;
	}
	public void setTurnOrder(List<Entity> turnOrder) {
		if(turnOrder.size() != 0) this.turnOrder = turnOrder;
	}
	public void beginEncounter(List<Player> encPlayers) {
		this.encPlayers = encPlayers;
		for(Player i : encPlayers) {
			if(i.isAlive()) i.setInCombat(true);
		}
		for(Enemy i: encEnemies) {
			i.setInCombat(true);
		}
		
	}
}
