package rpg.items;

public class Item implements Comparable <Item>{
	private String name = "";
	private int price = 0;
	private int level = 1;
	private String itemType = "Item";
	private String description = "";
	
	//Constructores
	public Item(String name, int price, int level) {
		this.setName(name);
		this.setPrice(price);
		this.setLevel(level);
	}
	
	//Setters
	public void setName(String name) {
		if (name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	
	public void setPrice(int price) {
		if (price >= 0) this.price = price;
	}
	
	public void setLevel(int level) {
		if (level >= 1) this.level = level;
	}
	
	public void setDescription(String description) {
		if (description != null) this.description = description;
	}
	
	protected void setItemType(String itemType) {
		this.itemType = itemType;
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
	
	public String getItemType() {
		return this.itemType;
	}
	
	public String getDescription() {
		return this.description;
	}
	//Methods
	public String toString() {
		return this.getName() + "\t$" + this.getPrice() + "\tLvl:" + this.getLevel() + "\tType: " + this.itemType;
	}
	
	public String toStringDetails() {
		if (this.getDescription().strip() == "") return this.toString();
		return this.toString() + "\n\tDescription: " + this.getDescription();
	}
	
	public boolean equals(Item i) {
		if (this.getClass() != i.getClass()) return false;
		return this.getName() == i.getName() && this.getPrice() == i.getPrice() 
				&& this.getLevel() == i.getLevel();
	}

	@Override
	public int compareTo(Item i) {
		int comp = this.getItemType().compareTo(i.getItemType());
		if (comp != 0) return comp;
		
		comp = this.getName().compareTo(i.getName());
		if (comp != 0) return comp;
		
		comp = Integer.compare(this.getLevel(), i.getLevel());
		if (comp != 0) return comp;
		
		return Integer.compare(this.getPrice(), i.getPrice());
	}
}
