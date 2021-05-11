package rpg.characters;

import java.util.List;
import java.util.TreeMap;

import rpg.items.*;
import rpg.specialities.*;

public class Player extends Entity{
	
	//Para Después
	public Player(String name, int hp, int mp, int lvl, String status, TreeMap<Skill, Integer> skills, TreeMap<Stats, Integer> stats,
			boolean alive, Item[] equipedItem, List<Entity> targets) {
		super(name, hp, mp, lvl, status, skills, stats, alive, equipedItem, targets);
		// TODO Auto-generated constructor stub
	}

	private Bag bag = new Bag();
	private static double sellRate = 0.80;
	private static int minToSellRate = 20;  
	private int xp = 0;
	private PlayerClass playerClass;
	private Armor armor;
	
	//gettters
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
	
	//Metodos
	public Item sellItem(Item item) {
		if (!bag.getItems().containsKey(item)) return null;
		int money = item.getPrice(); 
		if (money >= Player.minToSellRate) money = (int) (money*Player.sellRate);   
		bag.deleteItem(item);
		bag.setMoney(bag.getMoney() + money);
		return item;
	}

}
