package rpg.characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpg.RPG;
import rpg.items.Item;

public class Encounter {
	
	private RPG<?> rpg;
	private List<Player> encPlayers =  new ArrayList<Player>();
	private List<Enemy> encEnemies = new ArrayList<Enemy>();
	private List<Entity> turnOrder = new ArrayList<Entity>();
	
	//Contructor
	public Encounter(RPG<?> rpg, List<Enemy> encEnemies) {
		this.rpg = rpg;
		this.encEnemies = encEnemies;
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
	
	public RPG<?> getRPG() {
		return this.rpg;
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
	//Methods
	public void beginEncounter(List<Player> encPlayers) {
		
		for(Player p : encPlayers) {
			if(p.isAlive()) {
				p.setInCombat(true);
				this.encPlayers.add(p);
			}
		}
		this.setEncPlayers(encPlayers);
		this.turnOrder.addAll(encPlayers);
		this.turnOrder.addAll(encEnemies);
		Collections.shuffle(this.turnOrder);
	}
	
	public void endEncounter() {
		int totalExp = 0;
		int totalGold = 0;
		List <Item> itemDrops = new ArrayList<Item>();
		
		for(Enemy e : encEnemies) {
			e.setInCombat(false);
			if(!e.isAlive()) {
				totalExp += e.getXpDrop();
				totalGold += e.getGoldDrop();
				itemDrops.addAll(e.getItemDrop());
				e.setAlive(true);
				e.setHp(e.getStat(Stats.max_hp));
				e.setMp(e.getStat(Stats.max_mp));
				e.getTargets().clear();
			}
		}
		totalExp = totalExp/encPlayers.size();
		totalGold = totalGold/encPlayers.size();
		List <Player> winners = new ArrayList <Player>();
		for(Player p : encPlayers) {
			p.setInCombat(false);
			p.getTargets().clear();
			if(p.isAlive()) {
				p.setXp(p.getXp()+totalExp);
				p.getBag().setMoney(p.getBag().getMoney()+totalGold);
				winners.add(p);
			}
		}
		Random rnd = new Random();
		for (Item i: itemDrops) 
			winners.get(rnd.nextInt(winners.size())).getBag().addItem(i);
		this.encEnemies.clear();
		this.encPlayers.clear();
		this.turnOrder.clear();
		this.rpg.setCurrentEncounter(null);
	}
}
