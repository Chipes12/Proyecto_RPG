package rpg.items;

public class Armor extends Item {
	private int armorClass = 0;

	//Constructor
	public Armor(String name, int price, int level, int armorClass) {
		super(name, price, level);
		this.setArmorClass(armorClass);
	}

	//Setters
	public void setArmorClass(int armorClass) {
		if (armorClass >= 0) this.armorClass = armorClass;
	}
	
	//Getters
	public int getArmorClass() {
		return armorClass;
	}
	
	//Methods
	public String toStringDetails() {
		return super.toString() + "\n\tArmor Class: +" + this.getArmorClass();
	}
	
	@Override
	public boolean equals(Item i) {
		Armor a = (Armor) i;
		return super.equals(i) && this.getArmorClass() == a.getArmorClass();
	}
}
