package rpg.specialities;
import rpg.items.Weapon.WeaponEnum; 

public class Skill implements Comparable<Skill>{
	//Atributos default
	private String name = "";
	private int damage = 0;
	private int magicDamage = 0;
	private int mpCost = 0;
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
	public int getMinLevel() {
		return minLevel;
	}
	public boolean isLearnable() {
		return learnable;
	}
	public WeaponEnum getWeaponType() {
		return weaponType;
	}
	
	//Setters con condiciones para que no haya cadenas vacias ni numeros menores a 0
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
		if (weaponType == null) {
			this.weaponType = null;
			return;
		}
		if(weaponType.ordinal() >= WeaponEnum.MELEE.ordinal() && weaponType.ordinal() <= WeaponEnum.NONE.ordinal()) {
			this.weaponType = weaponType;
		}
	}
	
	//Constructor de una habilidad con todos sus atributos menos el nivel que en un inicio es 1
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
	
	//String sencillo aparecen sus daños y su nombre
	public String toString() {
		String str = this.getName() + "\t";
		if(this.damage > 0) str += "Damage: " + this.getDamage() + "\t";
		if(this.magicDamage > 0) str += "Magic Damage: " + this.getMagicDamage();
		return str;
	}
	//String completo tiene toda la información de la skill
	public String toStringDetails() {
		String str = this.toString() + "\n";
		str += "Mp Cost: " + this.getMpCost() + "\n";
		str += "Min level: " + this.getMinLevel() + "\n";
		str += "Needed weapon type: " + this.getWeaponType() + "\n";
		return str;
	}
	
	//Comparador que ordena de forma asendente de nivel para aprender y luego por orden alfabetico
	@Override
	public int compareTo(Skill o) {
		int comp = Integer.compare(this.getMinLevel(), o.getMinLevel());
		if(comp != 0) return comp;
		comp = this.getName().compareTo(o.getName());
		return comp;
		}
}
