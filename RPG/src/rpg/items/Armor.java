package rpg.items;

public class Armor extends Item {
	//Atributos default
	private int armorClass = 0;
	private final static String itemType = "Armor";

	//Constructor con super clase
	public Armor(String name, int price, int level, int armorClass) {
		super(name, price, level);
		this.setArmorClass(armorClass);
		this.setItemType(itemType);
	}

	//Setters que evitan que la variable sea negativa
	public void setArmorClass(int armorClass) {
		if (armorClass >= 0) this.armorClass = armorClass;
	}
	
	//Getters
	public int getArmorClass() {
		return armorClass;
	}
	
	//Methods
	
	//String detallado agrega la armadura
	@Override 
	public String toStringDetails() {
		return super.toStringDetails() + "\n\tArmor Class: +" + this.getArmorClass();
	}
	
	//Compara si son iguales desde super y verifica mismo valor de armorClass
	@Override
	public boolean equals(Item i) {
		Armor a = (Armor) i; 
		return super.equals(i) && this.getArmorClass() == a.getArmorClass();
	}
}
