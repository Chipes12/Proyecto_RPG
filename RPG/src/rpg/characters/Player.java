package rpg.characters;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rpg.RPG;
import rpg.items.*;
import rpg.specialities.*;

public class Player extends Entity implements Comparable <Player>{
	
	private Bag bag = new Bag();
	private PlayerClass playerClass = null;
	private Armor armor;
	private int xp = 0;
	private int statPoints = 5;
	private static int lvlRate = 55;
	private static int baseStatPoints = 2;
	private static double sellRate = 0.80;
	private static int minToSellRate = 20;  
	
	//Constructor
	public Player(String name) {
		super(name);
	}
	
	//Gettters
	public Bag getBag() {
		return this.bag;
	}
	public Armor getArmor() {
		return this.armor;
	}
	public double getSellRate() {
		return Player.sellRate;
	}
	public int getMinToSellRate() {
		return Player.minToSellRate;
	}
	public int getXp() {
		return this.xp;
	}
	public int getLvlRate() {
		return Player.lvlRate;
	}
	public int getBaseStatPoints() {
		return Player.baseStatPoints;
	}
	public int getStatPoints() {
		return this.statPoints;
	}
	public PlayerClass getPlayerClass() {
		return this.playerClass;
	}
	//setters 
	public static void setMinToSellRate(int minToSellRate) {
		Player.minToSellRate = minToSellRate;
	}
	public static void setSellRate(double sellRate) {
		if (sellRate > 0 && sellRate < 1) Player.sellRate = sellRate;
	}
	public void setXp(int xp) {
		if (this.getLvl() != RPG.getLevelCap() && xp >= 0) this.xp = xp;
	}
	public void setLvlRate(int lvlRate) {
		if (lvlRate >= 10) Player.lvlRate = lvlRate;
	}
	public void setBaseStatPoints(int baseStatPoints) {
		if (baseStatPoints >= 0) Player.baseStatPoints = baseStatPoints;
	}
	public void setStatPoints(int statPoints) {
		if (baseStatPoints >= 0) this.statPoints = statPoints;
	}
	//Methods
	public boolean sellItem(Item item) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if (!bag.getItems().containsKey(item)) return false;
		int money = item.getPrice(); 
		if (money >= Player.minToSellRate) money = (int) (money*Player.sellRate);   
		bag.deleteItem(item);
		bag.setMoney(bag.getMoney() + money);
		return false;
	}
	
	public boolean equipItem(Item item) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if(this.bag.getItems().get(item) == 1 && this.getEquippedItem()[0] != item && this.getEquippedItem()[1] != item) return false;
		if(!(this.bag.getItems().containsKey(item)) || this.getLvl() < item.getLevel()) return false;
		if(item instanceof Armor) {
			this.armor = (Armor) item;
			return true;
		}
		Item[] prevItems = this.getEquippedItem();
		Item[] newItems = new Item[2];
		newItems[0] = item;
		newItems[1] = prevItems[0];
		this.setEquippedItem(newItems);
		return true;
	}
	public Item unequipItem() {
		if (this.isInCombat() || !this.isAlive()) return null;
		Item[] prevItems = this.getEquippedItem();
		if(prevItems[0] == null && prevItems[1] == null) return null;
		if(prevItems[0] != null && prevItems[1] == null) {
			Item temp = prevItems[0];
			prevItems[1] = null;
			prevItems[0] = null;
			return temp;
		}
		Item temp = prevItems[0];
		prevItems[0] = prevItems[1];
		prevItems[1] = null;
		this.setEquippedItem(prevItems);
		return temp;
	}
	public boolean chooseClass(PlayerClass pc) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if(this.playerClass == null) {
			this.playerClass = pc;
			TreeMap <Stats, Integer> cStats = this.getStats();
			Stats[] cStatsA = cStats.keySet().toArray(new Stats[7]);
			pc.getStatModifiers();
			TreeMap <Stats, Integer> pStats = this.getStats();
			Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
			
			for(int i = 0; i < 7; i++)
				pStats.put(pStatsA[i], pStats.get(pStatsA[i]) + cStats.get(cStatsA[i]));
		}
		return true;
	}
	public void revive() {
		if(this.getHp() == 0 && this.isAlive() == false) {
			TreeMap <Stats, Integer> pStats = this.getStats();
			Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
			this.setHp(pStats.get(pStatsA[0])/2);
			this.setMp(pStats.get(pStatsA[1])/2);		
		}
	}

	public boolean useConsumable(Consumable cons) {
		if (!this.isAlive()) return false;
		if(!(this.bag.getItems().containsKey(cons)) || cons.getLevel() > this.getLvl()) return false;
		cons.boostStasts(this);
		bag.deleteItem(cons);
		return true;
	}
	
	public boolean levelUp() {
		if (RPG.getLevelCap() == this.getLvl()) return false;
		int capXP = this.getLvl()*Player.lvlRate;
		if (this.getXp() < capXP) return false;
		
		this.setXp(this.getXp() - capXP);
		this.setLvl(this.getLvl() + 1);
		this.setStatPoints(Player.baseStatPoints);
		
		if (RPG.getLevelCap() >= this.getLvl()) {
			this.setLvl(RPG.getLevelCap());
			this.setXp(0);
			this.setStatPoints(0);
		}
		return true;
	}
	
	public boolean levelStat(Stats stat) {
		if (!this.isAlive() || this.isInCombat() || this.getStatPoints() <= 0) return false;
		this.getStats().put(stat, this.getStats().get(stat) + 1);
		return true;
	}
	
	public boolean levelSkill(Skill skill) {
		if (!this.isAlive() || this.isInCombat()) return false;
		this.getSkills().put(skill, this.getSkills().get(skill) + 1);
		return true;
	}
	
	public int buyItem(Item item, int quantity) {
		Bag b = this.bag;
		int bagQ = b.getItems().entrySet().stream().mapToInt(Map.Entry::getValue).sum();
		if (bagQ == Bag.getBAG_SIZE()) return 0;
		for (int i=0; i<quantity; i++) {
			if (b.getMoney() >= item.getPrice() && bagQ +1 <= Bag.getBAG_SIZE()) {
				b.setMoney(b.getMoney() - item.getPrice());
				b.addItem(item);
			}else {
				return i;
			}
		}
		return quantity;
	}

	@Override
	public int compareTo(Player p) {
		int comp;
		if (this.getPlayerClass() != null && p.getPlayerClass() != null)
			comp = this.getPlayerClass().compareTo(p.getPlayerClass());
		else {
			if (this.getPlayerClass() == null && p.getPlayerClass() != null)
				return -1;
			else if (this.getPlayerClass() != null && p.getPlayerClass() == null)
				return 1;
			comp = 0;
		}
		
		if (comp != 0) return comp;
		
		return this.getName().compareTo(p.getName());
	}
	
	public boolean sellItem(Item item, int i ) {
		if (!this.bag.getItems().containsKey(item)) return false;
		this.bag.setMoney(this.bag.getMoney() + item.getPrice());
		this.bag.deleteItem(item);
		return true;
	}
	
	@Override
	public boolean learnSkill(Skill skill) {
		if (this.isInCombat() || !this.isAlive()) return false;
		if (this.getSkills().containsKey(skill)) return false;
		if(this.getLvl() >= skill.getMinLevel() && skill.isLearnable() && this.playerClass.getSkills().contains(skill)) {
			this.getSkills().put(skill, 1);
			return true;
		}
		return false;
	}
	@Override
	public void die() {
		if(this.getHp() == 0 && this.isAlive()) this.setAlive(false);
		this.setXp(0);
	}
	@Override
	public int attack(Skill skill, List<Entity> ptargets) {
		int totaldmg = super.attack(skill, ptargets);
		if (totaldmg == 0) return 0;

		int numTargets = skill.getMaxTargets();
		int tSize = (ptargets == null) ? 1: ptargets.size();
		if (numTargets-tSize > 0) numTargets = tSize;
		Enemy target;
		int defense = 0;
		for (int t=0; t<numTargets; t++) {
			defense = 0;
			if(this.getTargets().get(t).getClass() != this.getClass()) {
				target = (Enemy) this.getTargets().get(t);
				defense = target.getStats().get(Stats.defense);
				if (! target.isShielded()) 
					target.setHp(totaldmg - defense);
				target.setShielded(false);
			} 
		}
		return totaldmg;
	}

}
