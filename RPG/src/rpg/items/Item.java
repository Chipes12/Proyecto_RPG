package rpg.items;

public class Item implements Comparable <Item>{
	private String name = "";
	private int price = 0;
	private int level = 1;
	
	//Constructores
	public Item(String name, int price, int level) {
		this.setName(name);
		this.setPrice(price);
		this.setLevel(level);
	}
	
	//Setters
	public void setName(String name) {
		if (name != null && name.strip() != "") this.name = name;
	}
	
	public void setPrice(int price) {
		if (price >= 0) this.price = price;
	}
	
	public void setLevel(int level) {
		if (level >= 1) this.level = level;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getLevel() {
		return level;
	}
	
	//Methods
	public String toString() {
		return this.getName() + "\t$" + this.getPrice() + "\tLvl:" + this.getLevel();
	}
	
	public boolean equals(Item i) {
		if (this.getClass() != i.getClass()) return false;
		return this.getName() == i.getName() && this.getPrice() == i.getPrice() 
				&& this.getLevel() == i.getLevel();
	}

	@Override
	public int compareTo(Item i) {
		return this.getName().compareTo(i.getName());
	}
}
