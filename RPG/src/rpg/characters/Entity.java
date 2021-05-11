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
	
	//Condiciones de Sett para ver después
	public void setStatus(String status) {
		if(status != null && status.strip() != "" && status.length() <= 20) this.status = status;
	}
	public void setSkills(TreeMap<Skill, Integer> skills) {
		this.skills = skills;
	}
	public void setStats(TreeMap<Stats, Integer> stats) {
		this.stats = stats;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public void setEquipedItem(Item equipedItem) {
		this.equipedItem[1] = this.equipedItem[0];
		this.equipedItem[0] = equipedItem; 
	}
	public void setTargets(List<Entity> targets) {
		this.targets = targets;
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
		String str = this.getName() + "\n";
		str += this.getLvl() + "\n" + this.getHp() + "\t" + this.getMp();
		return str;
	}
	public String toStringDetails() {
		String str = this.toString() + "\n";
		str += this.getStats() + "\n";
		str += this.getSkills() + "\n";
		if(this.isAlive()) str += "Alive";
		else str += "Dead";
		return str;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Entity))
			return false;
		Entity other = (Entity) obj;
		if (alive != other.alive)
			return false;
		if (hp != other.hp)
			return false;
		if (lvl != other.lvl)
			return false;
		if (mp != other.mp)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		return true;
	}
	
}