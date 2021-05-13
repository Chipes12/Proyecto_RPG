package rpg.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import rpg.specialities.Skill;
import rpg.specialities.Skill.SkillEnum;
import rpg.items.Item;
import rpg.items.Weapon;
import rpg.items.Weapon.WeaponEnum;

public abstract class Entity implements Combat{
	
	private String name;
	private static int regenRate = 10;
	static int baseHp = 100;
	private static int baseMp = 100;
	private boolean inCombat = false;
	private boolean shielded = false;
    private int hp = baseHp;
    private int mp = baseMp;
    private int lvl = 1;
    private static TreeMap <Skill, Integer> baseSkills = new TreeMap<Skill, Integer>();
    private static TreeMap <Stats, Integer> baseStats = new TreeMap<Stats, Integer>();
    private TreeMap <Skill, Integer> skills = new TreeMap<Skill, Integer>();
    private TreeMap <Stats, Integer> stats = new TreeMap<Stats, Integer>();
    static {
    	baseSkills.put(new Skill("Punch", 5, 0, 0, 1, true, WeaponEnum.NONE, SkillEnum.ATTACK), 1);
    	baseStats.put(Stats.max_hp, baseHp);
    	baseStats.put(Stats.max_mp, baseMp);
    	baseStats.put(Stats.strength, 0);
    	baseStats.put(Stats.intelligence, 0);
    	baseStats.put(Stats.dexterity, 0);
    	baseStats.put(Stats.constitution, 0);
    	baseStats.put(Stats.defense, 0);
    }
    private boolean alive = true;
    private Item[] equippedItem = new Item [2];
    private List<Entity> targets = new ArrayList<Entity>();
    
	//Constructor
	public Entity(String name) {
		this.setName(name);
		this.skills.putAll(baseSkills);
		this.stats.putAll(baseStats);
	}
    
    //Getters
	public static int getRegenRate() {
		return Entity.regenRate;
	}
	public static int getBaseHp() {
		return Entity.baseHp;
	}
	public static int getBaseMp() {
		return Entity.baseMp;
	}
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
	public int getStat(Stats stat) {
		return this.getStats().get(stat); 
	}
	public static TreeMap<Skill, Integer> getBaseSkills(){
		return Entity.baseSkills;
	}
	public static TreeMap<Stats, Integer> getBaseStats(){
		return Entity.baseStats;
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
	public Item[] getEquippedItem() {
		return equippedItem;
	}
	public List<Entity> getTargets() {
		return targets;
	}
	public boolean isInCombat() {
		return this.inCombat;
	}
	public boolean isShielded() {
		return this.shielded;
	}
	//Setters con restricciones para que no existan numeros negativos o cadenas vacias
	public static void setRegenRate(int regenRate) {
		if(regenRate >= 0) Entity.regenRate = regenRate;
	}
	public static void setBaseHp(int baseHp) {
		if(baseHp > 0) Entity.baseHp = baseHp;
	}
	public static void setBaseMp(int baseMp) {
		if(baseMp > 0) Entity.baseHp = baseMp;
	}
	public void setName(String name) {
		if(name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	public void setHp(int hp) {
		if(hp >= 0 && this.alive) this.hp = hp;
	}
	public void setMp(int mp) {
		if(mp >= 0 && this.alive) this.mp = mp;
	}
	public void setLvl(int lvl) {
		if(lvl >= 1) this.lvl = lvl;
	}
	public static void setBaseSkills(TreeMap<Skill, Integer> baseSkills) {
		if(baseSkills.size() != 0) Entity.baseSkills = baseSkills;
	}
	public static void setBaseStats(TreeMap<Stats, Integer> baseStats) {
		if(baseSkills.size() == 7) Entity.baseStats = baseStats;
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
	public void setEquippedItem(Item[] equipedItem) {
		this.equippedItem = equipedItem;
	}
	public void setTargets(List<Entity> targets) {
		if(targets.size()!= 0) this.targets = targets;
	}
	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}
	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}
	//Methods
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
		if(!(this.skills.equals(nuevo.skills) && this.stats.equals(nuevo.stats))) return false;
		if(!(nuevo.alive == this.alive && this.equippedItem.equals(nuevo.equippedItem) && this.targets.equals(nuevo.targets))) return false;
		return true;
	}
	public void regen() {
		if (!this.alive) return;
		this.setMp(this.getMp() + Entity.regenRate);
		if(this.isInCombat()) this.setHp(this.getHp() + Entity.regenRate);
	}
	
	public boolean target(Entity entity) {
		if (!this.isInCombat() || !this.isAlive()) return false;
		if (entity instanceof Player) this.targets.add((Player)entity);
		if (entity instanceof Enemy) this.targets.add((Enemy)entity);
		return true;
	}
	
	public abstract void die();
	public abstract boolean learnSkill(Skill skill);
	
	@Override
	public int attack(Skill skill, List<Entity> ptargets) {
		int totalDamage = 0;
		boolean isAvaliable = false;
		totalDamage += this.getStats().get(Stats.strength);
		if(skill.getSkillType() != Skill.SkillEnum.ATTACK) return 0;
		if(this.getMp() < skill.getMpCost()) return 0;
				
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
			this.setMp(this.getMp() - skill.getMpCost());
			return totalDamage;
		}else {
			return 0;
		}
		
		
	}

	@Override
	public boolean defend(Skill skill) {
		if(this.getMp() < skill.getMpCost()) return false;
		if(skill.getSkillType() != Skill.SkillEnum.DEFENSE) return false;
		if(this.getMp() < skill.getMpCost()) return false;
		if(this.getEquippedItem()[0] != null) {
			if(this.getEquippedItem()[0].getItemType() == "Weapon") {
				Weapon w1 = (Weapon) this.getEquippedItem()[0];
				if(w1.getType() == WeaponEnum.SHIELD) {
					this.setShielded(true);
				}
			}
		}
		if(this.getEquippedItem()[1] != null) {
			if(this.getEquippedItem()[1].getItemType() == "Weapon") {
				Weapon w1 = (Weapon) this.getEquippedItem()[1];
				if(w1.getType() == WeaponEnum.SHIELD) {
					this.setShielded(true);
				}
			}
		}
		this.setMp(this.getMp() - skill.getMpCost());
		return true;
		
	}

	@Override
	public int heal(Skill skill) {
		// TODO Auto-generated method stub
		boolean healing = false;
		int totalHealing = 0;
		if(skill.getSkillType() != Skill.SkillEnum.HEAL) return 0;
		if(this.getMp() >= skill.getMpCost()) {
			if(this.getEquippedItem()[0] != null) {
				if(this.getEquippedItem()[0].getItemType() == "Weapon") {
					Weapon w1 = (Weapon) this.getEquippedItem()[0];
					if(w1.getType() == skill.getWeaponType()) {
						healing = true;
						totalHealing += w1.getMagicAttack() * this.getSkills().get(skill);
					}
				}
			}
			if(this.getEquippedItem()[1] != null) {
				if(this.getEquippedItem()[1].getItemType() == "Weapon") {
					Weapon w1 = (Weapon) this.getEquippedItem()[1];
					if(w1.getType() == skill.getWeaponType()) {
						healing = true;
						totalHealing += w1.getMagicAttack() * this.getSkills().get(skill);
					}
				}
			}
		}

		if(healing) {
			totalHealing += skill.getMagicDamage();
			if(totalHealing >= this.getStat(Stats.max_hp)) this.setHp(this.getStat(Stats.max_hp));
			else this.setHp(totalHealing + this.getHp());
		}
		this.setMp(this.getMp() - skill.getMpCost());
		return totalHealing;
	}
}