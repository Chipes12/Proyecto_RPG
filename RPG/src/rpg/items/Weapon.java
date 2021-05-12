package rpg.items;

public class Weapon extends Item{
	//Enum de Armas y sus clases
	public enum WeaponEnum{
		MELEE,
		RANGE,
		SHIELD,
		MAGICRANGE,
		MAGICMELEE;

	}
	//Atributos default
	private int attack = 0;
	private int magicAttack = 0;
	private int defense = 0;
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

	//Setters con condición para que los valores no sean negativos y que sean dentro del enum
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
	
	//Constructor con llamada a la super clase y definicion de todos los elementos
	public Weapon(String name, int price, int level, int attack, int magicAttack, int defense, WeaponEnum type) {
		super(name, price, level);
		this.setAttack(attack);
		this.setMagicAttack(magicAttack);
		this.setDefense(defense);
		this.setType(type);
		this.setItemType(itemType);
	}
	
	//Methods
	
	//toStringDetails convierte todo a un string con información adicional en caso de seleccionar un item
	public String toStringDetails() {
		String str = super.toStringDetails() + "\n";
		if(this.getAttack() > 0) {
			str += "Attack: " + this.getAttack() +"\n"; 
		}
		if(this.getMagicAttack() > 0) {
			str += "Magic Attack: " + this.getMagicAttack() + "\n"; 
		}
		if(this.getDefense() > 0) {
			str += "Defense: " + this.getDefense() + "\n"; 
		}
		str += this.getType();
		return str;
	}
	
	@Override
	public boolean equals(Item i) {
		if(!(i instanceof Weapon)) return false;
		Weapon a = (Weapon) i;
		return super.equals(i) && a.getAttack() == this.getAttack() && a.getDefense() == this.getDefense() && a.getMagicAttack() == this.getMagicAttack() && this.getType() == a.getType(); 
	}
	@Override
	public Item clone() {
		Weapon weapon = new Weapon(this.getName(), this.getPrice(), this.getLevel(), this.getAttack(), this.getMagicAttack(), this.getDefense(), this.getType());
		weapon.setDescription(this.getDescription());
		weapon.setItemType(this.getItemType());
		return weapon;
	}
	
}
