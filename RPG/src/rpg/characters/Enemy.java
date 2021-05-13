package rpg.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rpg.items.*;
import rpg.specialities.Skill;

public class Enemy extends Entity{

	private List<Item> itemDrop = new ArrayList<Item>();
	private int xpDrop = 5;
	private int goldDrop = 5;
	
	//Constructor
	public Enemy(String name, int lvl, List<Item> itemDrop, int xpDrop, int goldDrop) {
		super(name);
		this.setLvl(lvl);
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
		if(xpDrop > 0) this.xpDrop = xpDrop;
	}
	public void setGoldDrop(int goldDrop) {
		if(goldDrop > 0) this.goldDrop = goldDrop;
	}

	//Methods
	public Enemy clone() {
		Enemy enemy = new Enemy(this.getName(), this.getLvl(), this.getItemDrop(), this.getXpDrop(), this.getGoldDrop());
		enemy.setAlive(this.isAlive());
		enemy.setTargets(getTargets());
		enemy.setHp(this.getHp());
		enemy.setMp(this.getMp());
		enemy.setEquippedItem(this.getEquippedItem());
		enemy.setSkills(this.getSkills());
		return enemy;
	}

	@Override
	public boolean learnSkill(Skill skill) {
		return false;
	}
	public boolean learnSkill(Skill skill, int level) {
		if(this.getLvl() >= skill.getMinLevel()) {
			this.getSkills().put(skill, level);
			return true;
		}
		return false;
	}
	@Override
	public void die() {
		if(this.getHp() == 0 && this.isAlive()) this.setAlive(false);
	}

	@Override
	public int attack(Skill skill, List<Entity> ptargets) {
		int totaldmg = super.attack(skill, ptargets);
		if (totaldmg == 0) return 0;
		Random i = new Random();
		
		int numTargets = i.nextInt(skill.getMaxTargets())+1;
		int tSize = this.getTargets().size();
		if (numTargets-tSize > 0) numTargets = tSize;
		Player target;
		int random, defense = 0;
		for (int t=0; t<numTargets; t++) {
			defense = 0;
			random = i.nextInt(this.getTargets().size());
			if(this.getTargets().get(random).getClass() != this.getClass()) {
				target = (Player) this.getTargets().get(random);
				defense += target.getStats().get(Stats.defense);
				defense += target.getArmor().getArmorClass();
				if (! target.isShielded()) 
					target.setHp(totaldmg - defense);
		
				target.setShielded(false);
			} 
		}
		return totaldmg;
	}

	

}
