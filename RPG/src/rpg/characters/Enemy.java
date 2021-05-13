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
	public void attack(Skill skill) {
		// TODO Auto-generated method stub
		Random i = new Random();
		int random = i.nextInt(this.getTargets().size());

		int totalDamage = 0;
		boolean isAvaliable = false;
		totalDamage += this.getStats().get(Stats.strength);
		if(skill.getSkillType() != Skill.SkillEnum.ATTACK) return;
		
		if(this.getEquippedItem()[0] != null) {
			if(this.getEquippedItem()[0].getItemType() == "Weapon") {
				Weapon w1 = (Weapon) this.getEquippedItem()[0];
				totalDamage += w1.getAttack() + w1.getMagicAttack();
				if(skill.getWeaponType() == w1.getType()) {
					isAvaliable = true;
				}
			}
		}
		if(this.getEquippedItem()[1] != null) {
			if(this.getEquippedItem()[1].getItemType() == "Weapon") {
				Weapon w2 = (Weapon) this.getEquippedItem()[1];
				totalDamage += w2.getAttack() + w2.getMagicAttack();
				if(skill.getWeaponType() == w2.getType()) {
					isAvaliable = true;
				}
			}
		}
		if(isAvaliable) {
			totalDamage += (skill.getDamage() + skill.getMagicDamage()) * this.getSkills().get(skill);
		}
		
		if(this.getTargets().get(random).getClass() != this.getClass()) {
			Player a = (Player) this.getTargets().get(random);
			int defense = this.getTargets().get(random).getStats().get(Stats.defense);
			defense += a.getArmor().getArmorClass();
			this.getTargets().get(random).setHp(totalDamage - defense);
		}
	}

	@Override
	public void defend(Skill skill) {
		// TODO Auto-generated method stub
	}

	@Override
	public void heal(Skill skill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTarget() {
		
	}
}
