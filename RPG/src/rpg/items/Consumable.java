package rpg.items;

import java.util.TreeMap;
import rpg.characters.Stats;
import rpg.characters.Player;

public class Consumable extends Item{

	private int healAmount;
	private TreeMap<Stats, Integer> boostedStats = new TreeMap<>();
	private int duration;
	private final static String itemType = "Consumable";
	
	//Getters
 	public int getHealAmount() {
		return this.healAmount;
	}
	public TreeMap<Stats, Integer> getBoostedStats(){
		return this.boostedStats;
	}
	public int getDuration() {
		return this.duration;
	}
	
	//Setters
	public void setHealAmount(int healAmount) {
		if(healAmount >= 0) this.healAmount = healAmount;
	}	
	public void setBoostedStasts(TreeMap<Stats, Integer> boostedStats) {
		if(boostedStats.size()==7) this.boostedStats = boostedStats;
	}
	public void setDuration(int duration) {
		if(duration >= 0)this.duration = duration;
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
		TreeMap <Stats, Integer> pStats = player.getStats();
		Stats[] pStatsA = pStats.keySet().toArray(new Stats[7]);
		
		for(int i = 0; i < 7; i++)
			pStats.put(pStatsA[i], pStats.get(pStatsA[i]) + this.boostedStats.get(pStatsA[i]));
	}
	
	
}
