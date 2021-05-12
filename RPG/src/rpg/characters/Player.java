package rpg.characters;

import java.util.TreeMap;

import rpg.items.*;
import rpg.specialities.*;

public class Player extends Entity{
	
	private Bag bag = new Bag();
	private PlayerClass playerClass = null;
	private Armor armor;
	private int xp = 0;
	private static double sellRate = 0.80;
	private static int minToSellRate = 20;  
	
	//Constructor
	public Player(String name) {
		super(name);
	}
	
	//Gettters
	public Armor getArmor() {
		return this.armor;
	}
	public static double getSellRate() {
		return sellRate;
	}
	public static int getMinToSellRate() {
		return minToSellRate;
	}
	public int getXp() {
		return this.xp;
	}
	
	//setters 
	public static void setMinToSellRate(int minToSellRate) {
		Player.minToSellRate = minToSellRate;
	}
	public static void setSellRate(double sellRate) {
		if (sellRate > 0 && sellRate < 1) Player.sellRate = sellRate;
	}
	public void setXp(int xp) {
		if(xp >= 0) this.xp = xp;
	}
	
	//Methods
	public boolean sellItem(Item item) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if (!bag.getItems().containsKey(item)) return false;
		int money = item.getPrice(); 
		if (money >= Player.minToSellRate) money = (int) (money*Player.sellRate);   
		bag.deleteItem(item);
		bag.setMoney(bag.getMoney() + money);
		return false;
	}
	@Override
	public boolean learnSkill(Skill skill) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if(this.getLvl() >= skill.getMinLevel() && skill.isLearnable()) {
			this.getSkills().put(skill, 1);
			return true;
		}
		return false;
	}
	public boolean equipItem(Item item) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if(!(this.bag.getItems().containsKey(item))) return false;
		if(item instanceof Armor) {
			this.armor = (Armor) item;
			return true;
		}
		Item[] prevItems = this.getEquippedItem();
		Item[] newItems = new Item[2];
		newItems[0] = item;
		newItems[1] = prevItems[0];
		this.setEquippedItem(newItems);
		return true;
	}
	public boolean unequipItem() {
		if (this.isInCombat() || !this.isAlive()) return false;
		Item[] prevItems = this.getEquippedItem();
		if(prevItems[0] == null && prevItems[1] == null) return false;
		if(prevItems[0] != null && prevItems[1] == null) {
			prevItems[1] = null;
			prevItems[0] = null;
			return true;
		}
		prevItems[0] = prevItems[1];
		prevItems[1] = null;
		this.setEquippedItem(prevItems);
		return true;
	}
	public void chooseClass(PlayerClass pc) {
		if (this.isInCombat() || !this.isAlive()) return;
		if(this.playerClass == null) {
			this.playerClass = pc;
		}
	}
	public void revive() {
		if(this.getHp() == 0 && this.isAlive() == false) {
			TreeMap <Stats, Integer> pStats = this.getStats();
			Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
			this.setHp(pStats.get(pStatsA[0])/2);
			this.setMp(pStats.get(pStatsA[1])/2);		
		}
	}
	public void die() {
		if(this.getHp() == 0 && this.isAlive()) this.setAlive(false);
		this.setXp(0);
	}
	public boolean useConsumable(Consumable cons) {
		if (!this.isAlive()) return false;
		if(!(this.bag.getItems().containsKey(cons))) return false;
		cons.boostStasts(this);
		bag.deleteItem(cons);
		return true;
	}
	
}
