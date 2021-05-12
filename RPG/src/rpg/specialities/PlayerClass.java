package rpg.specialities;

import java.util.TreeMap;
import java.util.TreeSet;
import rpg.characters.Stats;

public class PlayerClass {
	
	private String name;
	private TreeSet<Skill> skills = new TreeSet<Skill>();
	private TreeMap<Stats, Integer> statModifiers = new TreeMap<>();
	
	//Constructor
	public PlayerClass(String name, TreeSet<Skill> skills, TreeMap<Stats, Integer> statModifiers) {
		this.setName(name);
		this.setSkills(skills);
		this.setStatModifiers(statModifiers);
	}
	
	public PlayerClass(String name, TreeSet<Skill> skills, int hp, int mp, int str, int inte, int dex, int con, int def) {
		this.setName(name);
		this.setSkills(skills);
		TreeMap<Stats, Integer> temp = new TreeMap<Stats,Integer>();
		temp.put(Stats.max_hp, hp);
		temp.put(Stats.max_mp, mp);
		temp.put(Stats.strength, str);
		temp.put(Stats.intelligence, inte);
		temp.put(Stats.dexterity, dex);
		temp.put(Stats.constitution, con);
		temp.put(Stats.defense, def);
		this.setStatModifiers(temp);
	}
	
	//Getters
	public String getName() {
		return name;
	}
	public TreeSet<Skill> getSkills() {
		return skills;
	}
	public TreeMap<Stats, Integer> getStatModifiers() {
		return statModifiers;
	}

	//Setters
	public void setName(String name) {
		if(name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	public void setSkills(TreeSet<Skill> skills) {
		if(skills.size() != 0) this.skills = skills;
	}
	public void setStatModifiers(TreeMap<Stats, Integer> statModifiers) {
		if(statModifiers.size() == 7) this.statModifiers = statModifiers;
	}

	//Methods
	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}
	public void modifyStatModifiers(Stats stat, int newValue) {
		if(newValue >= 0) this.statModifiers.put(stat, newValue);
	}
	public String toString() {
		String str = this.name + "\n\nSkills: \n";
		String strSkills = this.getSkills().toString();
		String [] arraySkills = strSkills.split(", ");
		for(String i : arraySkills) {
			if(i.startsWith("[")) i = i.substring(1);
			if(i.endsWith("]")) i = i.substring(0, i.length()-1);
			str += i + "\n";
		}
		str += "\nStats Modifiers: \n\n";
		String strStats = this.getStatModifiers().toString();
		String [] arrayStats = strStats.split(", ");
		for(String i : arrayStats) {
			if(i.startsWith("{")) i = i.substring(1);
			if(i.endsWith("}")) i = i.substring(0, i.length()-1);
			str += i + "\n";
		}
		return str;
	}
}
