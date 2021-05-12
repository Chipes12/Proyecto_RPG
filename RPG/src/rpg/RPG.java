package rpg;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import rpg.characters.*;
import rpg.items.*;
import rpg.items.Weapon.WeaponEnum;
import rpg.specialities.*;
import rpg.specialities.Skill.SkillEnum;

public abstract class RPG <T> {
	private Shop shop = new Shop();
	private TreeSet <Player <?>> players = new TreeSet<Player<?>>();
	private TreeSet <Enemy> enemies = new TreeSet<Enemy>();
	private TreeSet <Item> items = new TreeSet<Item>();
	private TreeSet <PlayerClass> classes = new TreeSet<PlayerClass>();
	private TreeSet <Skill> skills = new TreeSet <Skill>();
	private static int levelCap = 100;
	
	//Constructores
	public RPG() {};
	
	//Setters
	public void setShop(Shop shop) {
		if (shop != null) this.shop = shop;
	}
	
	public void setPlayers(TreeSet<Player<?>> players) {
		if (shop != null) this.players = players;
	}
	
	public void setEnemies(TreeSet<Enemy> enemies) {
		if (shop != null) this.enemies = enemies;
	}

	public void setItems(TreeSet<Item> items) {
		if (shop != null) this.items = items;
	}
	
	public void setClasses(TreeSet<PlayerClass> classes) {
		if (shop != null) this.classes = classes;
	}
	
	public void setSkills(TreeSet<Skill> skills) {
		if (shop != null) this.skills = skills;
	}

	public static void setLevelCap(int levelCap) {
		if (levelCap >= 10) RPG.levelCap = levelCap;
	}
	
	//Getters
	public Shop getShop() {
		return shop;
	}

	public TreeSet<Player<?>> getPlayers() {
		return players;
	}

	public TreeSet<Enemy> getEnemies() {
		return enemies;
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
	
	//Methods
	public void addPlayer(Player <?> player) {
		if (player != null) this.players.add(player);
	}
	
	public void addPlayer(T iD, String name) {
		this.players.add(new Player<T>(name, iD));
	}
	
	public void addEnemy(Enemy enemy) {
		if (enemy != null) this.enemies.add(enemy);
	}
	
	public void addEnemy (String name, int lvl, List<Item> itemDrop, int xpDrop, int goldDrop) {
		this.enemies.add(new Enemy(name, lvl, itemDrop, xpDrop, goldDrop));
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
}