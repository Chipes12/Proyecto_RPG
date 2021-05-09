package rpg.items;

public class Weapon extends Item{
	public enum WeaponEnum{
		MELEE,
		RANGE,
		SHIELD,
		MAGICRANGE,
		MAGICMELEE;

	}
	private int attack;
	private int magicAttack;
	private int defense;
	private WeaponEnum type;
	private final static String itemType = "Weapon";
	
	//getters
	public int getAttack() {
		return attack;
	}
	public int getMagicAttack() {
		return magicAttack;
	}
	public int getDefense() {
		return defense;
	}
	public WeaponEnum getType() {
		return type;
	}

	//Setters
	public void setAttack(int attack) {
		if(attack > 0) {
			this.attack = attack;
		}
	}	
	public void setMagicAttack(int magicAttack) {
		if(magicAttack > 0) {
			this.magicAttack = magicAttack;
		}
	}
	
public void setDefense(int defense) {
		if(defense > 0)	this.defense = defense;
	}
	public void setType(WeaponEnum type) {
		if(type.ordinal() >= WeaponEnum.MELEE.ordinal() && type.ordinal() <= WeaponEnum.MAGICMELEE.ordinal()) {
			this.type = type;
		}
	}
	
	public Weapon(String name, int price, int level, int attack, int magicAttack, int defense, WeaponEnum type) {
		super(name, price, level);
		this.setAttack(attack);
		this.setMagicAttack(magicAttack);
		this.setDefense(defense);
		this.setType(type);
		this.setItemType(itemType);
	}
	
	//Methods
	public String toStringDetails() {
		String str = super.toStringDetails() + "\n\t";
		if(this.getAttack() > 0) {
			str += "Attack: " + this.getAttack() +"\n\t"; 
		}
		if(this.getMagicAttack() > 0) {
			str += "Magic Attack: " + this.getMagicAttack() + "\n\t"; 
		}
		if(this.getDefense() > 0) {
			str += "Defense: " + this.getDefense() + "\n\t"; 
		}
		str += this.getType();
		return str;
	}
	
	@Override
	public boolean equals(Item i) {
		Weapon a = (Weapon) i;
		return super.equals(i) && a.getAttack() == this.getAttack() && a.getDefense() == this.getDefense() && a.getMagicAttack() == this.getMagicAttack() && this.getType() == a.getType(); 
	}
	
}
