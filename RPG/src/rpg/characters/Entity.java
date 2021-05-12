package rpg.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import rpg.specialities.Skill;
import rpg.items.Item;

public abstract class Entity {
	
	private String name;
    private int hp;
    private int mp;
    private int lvl = 1;
    private String status;
    private TreeMap <Skill, Integer> skills = new TreeMap<Skill, Integer>();
    private TreeMap <Stats, Integer> stats = new TreeMap<Stats, Integer>();
    private boolean alive;
    private Item[] equipedItem = new Item [2];
    private List<Entity> targets = new ArrayList<Entity>();
    
    //Getters
    public String getName() {
    	return this.name;
    }
    public int getHp() {
		return hp;
	}
	public int getMp() {
		return mp;
	}
	public int getLvl() {
		return lvl;
	}
	public String getStatus() {
		return status;
	}
	public TreeMap<Skill, Integer> getSkills() {
		return skills;
	}
	public TreeMap<Stats, Integer> getStats() {
		return stats;
	}
	public boolean isAlive() {
		return alive;
	}
	public Item[] getEquipedItem() {
		return equipedItem;
	}
	public List<Entity> getTargets() {
		return targets;
	}
	
	
	//Setters con restricciones para que no existan numeros negativos o cadenas vacias
	public void setName(String name) {
		if(name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	public void setHp(int hp) {
		if(hp >= 0) this.hp = hp;
	}
	public void setMp(int mp) {
		if(mp >= 0) this.mp = mp;
	}
	public void setLvl(int lvl) {
		if(lvl >= 1) this.lvl = lvl;
	}
	public void setStatus(String status) {
		if(status != null && status.strip() != "" && status.length() <= 20) this.status = status;
	}

	public void setSkills(TreeMap<Skill, Integer> skills) {
		if(skills.size() != 0) this.skills = skills;
	}
	public void setStats(TreeMap<Stats, Integer> stats) {
		if(stats.size() == 7) this.stats = stats;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public void setEquipedItem(Item[] equipedItem) {
		this.equipedItem = equipedItem;
	}
	public void setTargets(List<Entity> targets) {
		if(targets.size()!= 0) this.targets = targets;
	}
	
	
	//Constructor
	public Entity(String name, int hp, int mp, int lvl, String status, TreeMap<Skill, Integer> skills, TreeMap<Stats, Integer> stats,
			boolean alive, Item[] equipedItem, List<Entity> targets) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.lvl = lvl;
		this.status = status;
		this.skills = skills;
		this.stats = stats;
		this.alive = alive;
		this.equipedItem = equipedItem;
		this.targets = targets;
	}

	
	//LearnSkill por analizar
	public boolean learnSkill(Skill skill) {
		if(this.lvl >= skill.getMinLevel() && skill.isLearnable()) {
			this.skills.put(skill, 1);
			return true;
		}
		return false;
	}
	
	public void die() {
		if(this.hp == 0 && this.isAlive()) this.alive = false;
	}
	public String toString() {
		String str = "Name: " + this.getName() + "\n";
		str += "Level: " + this.getLvl() + "\nHP: " + this.getHp() + "\tMP: " + this.getMp();
		return str;
	}
	
	public String toStringDetails() {
		String str = this.toString() + "\nStats: \n\n";
		String strStats = this.getStats().toString();
		String [] arrayStats = strStats.split(", ");
		for(String i : arrayStats) {
			if(i.startsWith("{")) i = i.substring(1);
			if(i.endsWith("}")) i = i.substring(0, i.length()-1);
			str += i + "\n";
		}
		str += "\nSkills: \n\n";
		String strSkills = this.getSkills().toString();
		String [] arraySkills = strSkills.split(", ");
		for(String i : arraySkills) {
			if(i.startsWith("{")) i = i.substring(1);
			if(i.endsWith("}")) i = i.substring(0, i.length()-1);
			str += i + "\n";
		}
		str += "\n";
		if(this.isAlive()) str += "State: Alive";
		else str += "State: Dead";
		return str;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Entity)) return false;
		Entity nuevo = (Entity) obj;
		if(!(nuevo.hp == this.hp && nuevo.mp == this.mp && nuevo.name == this.name && nuevo.lvl == this.lvl)) return false;
		if(!(nuevo.status == this.status && this.skills.equals(nuevo.skills) && this.stats.equals(nuevo.stats))) return false;
		if(!(nuevo.alive == this.alive && this.equipedItem.equals(nuevo.equipedItem) && this.targets.equals(nuevo.targets))) return false;
		return true;
	}	
}