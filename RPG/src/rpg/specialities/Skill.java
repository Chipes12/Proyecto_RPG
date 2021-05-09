package rpg.specialities;
import rpg.items.Weapon.WeaponEnum; 

public class Skill {
	private String name = "";
	private int damage = 0;
	private int magicDamage = 0;
	private int mpCost = 0;
	private int level = 1;
	private int minLevel = 1;
	private boolean learnable = true;
	private WeaponEnum weaponType;
	
	//Getters
	public String getName() {
		return name;
	}
	public int getDamage() {
		return damage;
	}
	public int getMagicDamage() {
		return magicDamage;
	}
	public int getMpCost() {
		return mpCost;
	}
	public int getLevel() {
		return level;
	}
	public int getMinLevel() {
		return minLevel;
	}
	public boolean isLearnable() {
		return learnable;
	}
	public WeaponEnum getWeaponType() {
		return weaponType;
	}
	
	//Setters
	public void setName(String name) {
		if (name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	public void setDamage(int damage) {
		if(damage >= 0) this.damage = damage;
	}
	public void setMagicDamage(int magicDamage) {
		if(this.magicDamage >= 0) this.magicDamage = magicDamage;
	}
	public void setMpCost(int mpCost) {
		if(mpCost >= 0) this.mpCost = mpCost;
	}
	public void setMinLevel(int minLevel) {
		if(minLevel >= 1) this.minLevel = minLevel;
	}
	public void setLearnable(boolean learnable) {
		this.learnable = learnable;
	}
	public void setWeaponType(WeaponEnum weaponType) {
		if(weaponType.ordinal() >= WeaponEnum.MELEE.ordinal() && weaponType.ordinal() <= WeaponEnum.MAGICMELEE.ordinal()) {
			this.weaponType = weaponType;
		}
	}
	
	public Skill(String name, int damage, int magicDamage, int mpCost, int minLevel, boolean learnable,
			WeaponEnum weaponType) {
		this.setName(name);
		this.setDamage(damage);
		this.setMagicDamage(magicDamage);
		this.setMpCost(mpCost);
		this.setMinLevel(minLevel);
		this.setLearnable(learnable);
		this.setWeaponType(weaponType);
	}
	
	public String toString() {
		String str = this.getName() + "\t";
		if(this.damage > 0) str += "Damage: " + this.getDamage() + "\t";
		if(this.magicDamage > 0) str += "Magic Damage: " + this.getMagicDamage();
		return str;
	}
	public String toStringDetails() {
		String str = this.toString() + "\n";
		str += "Level: " + this.getLevel() + "\n";
		str += "Mp Cost: " + this.getMpCost() + "\n";
		str += "Min level: " + this.getMinLevel() + "\n";
		str += "Needed weapon type: " + this.getWeaponType() + "\n";
		return str;
	}
}
