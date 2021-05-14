package rpg.items;

import java.util.TreeMap;
import rpg.characters.Stats;
import rpg.characters.Player;

public class Consumable extends Item{

	private int healAmount = 0;
	private TreeMap<Stats, Integer> boostedStats = new TreeMap<Stats,Integer>();
	private boolean durationLimit = true;
	private static long baseDuration = 1000;
	private long duration = baseDuration;
	private final static String itemType = "Consumable";
	
	//Constructor
	public Consumable(String name, int price, int level) {
		super(name,price,level);
		TreeMap<Stats, Integer> temp = new TreeMap<Stats,Integer>();
		temp.put(Stats.max_hp, 0);
		temp.put(Stats.max_mp, 0);
		temp.put(Stats.strength, 0);
		temp.put(Stats.intelligence, 0);
		temp.put(Stats.dexterity, 0);
		temp.put(Stats.constitution, 0);
		temp.put(Stats.defense, 0);
		this.setBoostedStasts(temp);
		this.setItemType(itemType);
	}
	
	public Consumable(String name, int price, int level, TreeMap<Stats, Integer> boostedStats) {
		super(name, price, level);
		this.setBoostedStasts(boostedStats);
	}
	
	public Consumable(String name, int price, int level, int healAmount) {
		super(name, price, level);
		this.setHealAmount(healAmount);
	}
	
	public Consumable(String name, int price, int level, int hp, int mp, int str, int inte, int dex, int con, int def) {
		super(name, price, level);
		TreeMap<Stats, Integer> temp = new TreeMap<Stats,Integer>();
		temp.put(Stats.max_hp, hp);
		temp.put(Stats.max_mp, mp);
		temp.put(Stats.strength, str);
		temp.put(Stats.intelligence, inte);
		temp.put(Stats.dexterity, dex);
		temp.put(Stats.constitution, con);
		temp.put(Stats.defense, def);
		this.setBoostedStasts(temp);
		this.setItemType(itemType);
	}
	
	//Getters
 	public int getHealAmount() {
		return this.healAmount;
	}
	public TreeMap<Stats, Integer> getBoostedStats(){
		return this.boostedStats;
	}
	public long getDuration() {
		return this.duration;
	}
	public boolean hasTimeLimit() {
		return this.durationLimit;
	}
	public int getBaseDuration() {
		return this.getBaseDuration();
	}
	//Setters
	public void setHealAmount(int healAmount) {
		if(healAmount >= 0) this.healAmount = healAmount;
	}	
	public void setBoostedStasts(TreeMap<Stats, Integer> boostedStats) {
		if(boostedStats.size()==7) this.boostedStats = boostedStats;
	}
	public void setDuration(int duration) {
		if(duration >= 0) this.duration = duration;
	}
	public void setTimeLimit(boolean durationLimit) {
		this.durationLimit = durationLimit;
	}
	public void setBaseDuration(long baseDuration) {
		Consumable.baseDuration = baseDuration;
	}
	
	//Constructor
	public Consumable(String name, int price, int level, int healAmount, TreeMap<Stats, Integer> boostedStats, int duration) {
		super(name, price, level);
		this.setBoostedStasts(boostedStats);
		this.setDuration(duration);
		this.setHealAmount(healAmount);
		this.setItemType(itemType);
	}
	
	//Methods
	public boolean equals(Item i) {
		if(!(i instanceof Consumable)) return false;
		Consumable a = (Consumable) i;
		return super.equals(i) && this.getHealAmount() == a.getHealAmount() && this.getDuration() == a.getDuration() && this.boostedStats.equals(a.getBoostedStats());
	}
	public String toStringDetails() {
		String str = super.toStringDetails();
		if(this.getHealAmount() > 0) str += "Heals: " + this.getHealAmount() + " Hp";
		
		str += "\nBoosted Stats: \n\n";
		String strStats = this.getBoostedStats().toString();
		String [] arrayStats = strStats.split(", ");
		for(String i : arrayStats) {
			if(i.startsWith("{")) i = i.substring(1);
			if(i.endsWith("}")) i = i.substring(0, i.length()-1);
			str += i + "\n";
			}
		return str;
	}
	
	public void boostStasts(Player player) {
		player.setHp(player.getHp() + this.healAmount);
		TreeMap <Stats, Integer> pStats = player.getStats();
		Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
		
		for(int i = 0; i < 7; i++)
			pStats.put(pStatsA[i], pStats.get(pStatsA[i]) + this.boostedStats.get(pStatsA[i]));
	}
	
	public void unboostStasts(Player player) {
		TreeMap <Stats, Integer> pStats = player.getStats();
		Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
		
		for(int i = 0; i < 7; i++)
			pStats.put(pStatsA[i], pStats.get(pStatsA[i]) - this.boostedStats.get(pStatsA[i]));
	}
	
}
