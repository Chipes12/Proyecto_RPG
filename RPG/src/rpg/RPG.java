package rpg;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import rpg.characters.*;
import rpg.items.*;
import rpg.items.Weapon.WeaponEnum;
import rpg.specialities.*;
import rpg.specialities.Skill.SkillEnum;

public abstract class RPG <T>{
	private Shop shop;
	private TreeMap <T, Player> players = new TreeMap<T, Player>();
	private List <Enemy> enemies = new ArrayList<Enemy>();
	private TreeSet <Item> items = new TreeSet<Item>();
	private TreeSet<PlayerClass> classes = new TreeSet<PlayerClass>();
	private TreeSet <Skill> skills = new TreeSet <Skill>();
	private List <Encounter> encounters = new ArrayList <Encounter>();
	private Encounter currentEncounter;
	private static int levelCap = 100;
	
	//Constructores
	public RPG() {
		this.setShop(new Shop(this));
	};
	
	//Setters
	public void setShop(Shop shop) {
		if (shop != null) {
			this.shop = shop;
			shop.setRPG(this);
		}
	}
	
	public void setPlayers(TreeMap<T, Player> players) {
		if (players != null) this.players = players;
	}
	
	public void setEnemies(List<Enemy> enemies) {
		if (enemies != null) this.enemies = enemies;
	}

	public void setItems(TreeSet<Item> items) {
		if (items != null) this.items = items;
	}
	
	public void setClasses(TreeSet<PlayerClass> classes) {
		if (classes != null) this.classes = classes;
	}
	
	public void setSkills(TreeSet<Skill> skills) {
		if (skills != null) this.skills = skills;
	}

	public static void setLevelCap(int levelCap) {
		if (levelCap >= 10) RPG.levelCap = levelCap;
	}
	
	public void setCurrentEncounter(Encounter currentEncounter) {
		this.currentEncounter = currentEncounter;
	}
	
	//Getters
	public Shop getShop() {
		return this.shop;
	}

	public TreeMap<T, Player> getPlayers() {
		return players;
	}

	public List<Enemy> getEnemies() {
		return this.enemies;
	}

	public TreeSet<Item> getItems() {
		return items;
	}

	public TreeSet<PlayerClass> getClasses() {
		return classes;
	}

	public TreeSet<Skill> getSkills() {
		return skills;
	}
	
	public static int getLevelCap() {
		return levelCap;
	}
	
	public List<Encounter> getEncounters(){
		return this.encounters;
	}
	
	public Encounter getCurrentEncounter() {
		return this.currentEncounter;
	}
	
	//Methods
	public void addPlayer(T iD, Player player) {
		if (player != null) this.players.put(iD, player);
	}
	
	public void addPlayer(T iD, String name) {
		Player p = new Player(name);
		this.players.put(iD, p);
	}
	
	public void addEnemy(Enemy enemy) {
		if (enemy != null) this.enemies.add(enemy);
	}
	
	public void addEnemy (String name, int lvl, List<Item> itemDrop, int xpDrop, int goldDrop) {
		this.enemies.add(new Enemy(name, lvl, itemDrop, xpDrop, goldDrop));
	}
	
	public void addEncounter(List<Enemy> encEnemies) {
		this.encounters.add(new Encounter(this, encEnemies));
	}
	
	public void beginEncounter(int encIndex, List<Player> encPlayers) {
		if (encIndex >= 0 && encIndex < this.encounters.size()) {
			this.encounters.get(encIndex).beginEncounter(encPlayers);
			this.currentEncounter = this.encounters.get(encIndex);
		}
	}
	
	public void addClass(PlayerClass Pclass) {
		if (Pclass != null) this.classes.add(Pclass);
	}	
	
	public void addClass(String name, TreeSet<Skill> skills, TreeMap<Stats, Integer> statModifiers) {
		this.classes.add(new PlayerClass(name,skills, statModifiers));
	}	
	
	public void addClass(String name, TreeSet<Skill> skills,  int hp, int mp, int str, int inte, int dex, int con, int def) {
		this.classes.add(new PlayerClass(name,skills, hp, mp,str,inte,dex,con,def));
	}	

	public void addItem(Item item) {
		if (item != null) this.items.add(item);
	}
	
	public void addItem(String name, int price, int level) {
		this.items.add(new Item (name, price, level));
	}
	
	public void addSkill(Skill skill) {
		if (skill != null) this.skills.add(skill);
	}
	
	public void addSkill(String name, int damage, int magicDamage, int mpCost, int minLevel, boolean learnable,
			WeaponEnum weaponType, SkillEnum skillType) {
		this.skills.add(new Skill(name, damage, magicDamage, mpCost, minLevel, learnable, weaponType, skillType));
	}
	
	public String toStringPlayers() {
		String str = "";
		Object[] playersA = this.players.keySet().toArray(new Object[this.players.size()]);
		for (Object i: playersA) {
			str += "ID: " + i.toString() + "\n";
			str += this.players.get(i)  + "\n\n";
		}	
		return str;
	}
	public String toStringEnemies() {
		String str = "";
		for (Enemy i: this.enemies)
			str += i.toString() + "\n\n";
		return str;
	}
	public String toStringItems() {
		String str = "";
		for (Item i: this.items)
			str += i.toString() + "\n";
		return str;
	}
	public String toStringClasses() {
		String str = "";
		int j = 1;
		for (PlayerClass i: this.classes)
			str += j + ") " + i.toString() + "\n";
		return str;
	}
	public String toStringSkills() {
		String str = "";
		for (Skill i: this.skills)
			str += i.toString() + "\n";
		return str;
	}
	
	public String toStringEncounters() {
		String str = "";
		for (Encounter i: this.encounters) {
			str += "----ENCOUNTER #" + this.encounters.indexOf(i) + "----\n\n";
			str += i.toString() + "\n";
		}
			
		return str;
	}
}