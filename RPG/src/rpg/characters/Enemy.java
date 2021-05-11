package rpg.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import rpg.items.Item;
import rpg.specialities.Skill;

public class Enemy extends Entity{

	private List<Item> itemDrop = new ArrayList<Item>();
	private int xpDrop;
	private int goldDrop;
	
	public Enemy(String name, int hp, int mp, int lvl, String status, TreeMap<Skill, Integer> skills, TreeMap<Stats, Integer> stats,
			boolean alive, Item[] equipedItem, List<Entity> targets, List<Item> itemDrop, int xpDrop, int goldDrop) {
		super(name, hp, mp, lvl, status, skills, stats, alive, equipedItem, targets);
		// TODO Auto-generated constructor stub
		this.setGoldDrop(goldDrop);
		this.setXpDrop(xpDrop);
		this.setItemDrop(itemDrop);
	}

	//Getters
	public List<Item> getItemDrop() {
		return itemDrop;
	}
	public int getXpDrop() {
		return xpDrop;
	}
	public int getGoldDrop() {
		return goldDrop;
	}
	
	//Setters
	public void setItemDrop(List<Item> itemDrop) {
		this.itemDrop = itemDrop;
	}
	public void setXpDrop(int xpDrop) {
		this.xpDrop = xpDrop;
	}
	public void setGoldDrop(int goldDrop) {
		this.goldDrop = goldDrop;
	}

	public Enemy clone() {
		Enemy enemy = new Enemy(this.getName(), this.getHp(), this.getMp(), this.getLvl(), this.getStatus(), this.getSkills(), this.getStats(), this.isAlive(), this.getEquipedItem(), this.getTargets(), this.getItemDrop(), this.getXpDrop(), this.getGoldDrop());
		return enemy;
	}
}
