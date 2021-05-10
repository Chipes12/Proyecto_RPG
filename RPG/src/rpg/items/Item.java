package rpg.items;

public class Item implements Comparable <Item>{
	
	//Atributos default
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
	
	//Setters con condiciones para que sean positivos y distintos a cadenas vacias
	public void setName(String name) {
		if (name != null && name.strip() != "" && name.length() <= 20) this.name = name;
	}
	
	public void setPrice(int price) {
		if (price >= 0) this.price = price;
	}
	
	public void setLevel(int level) {
		if (level >= 1) this.level = level;
	}
	
	//Setter adicional en caso de que se quiera añadir una leyenda o descripción al Item
	public void setDescription(String description) {
		if (description != null) this.description = description;
	}
	
	//Metodo que nos ayuda a distinguir el nombre de tipo y tenerlo como string
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
	
	//toString sólo da la información basica de un item que son su precio, nivel, nombre y tipo de objeto
	public String toString() {
		return this.getName() + "\t$" + this.getPrice() + "\tLvl:" + this.getLevel() + "\tType: " + this.itemType;
	}
	
	//toStringDetails se sobreescribira más adelante aquí sólamente se le añade la leyenda si es que hay una
	public String toStringDetails() {
		if (this.getDescription().strip() == "") return this.toString();
		return this.toString() + "\n\tDescription: " + this.getDescription();
	}
	
	//Debe ser la misma clase y tener los mismos 3 atributos principales
	public boolean equals(Item i) {
		if (this.getClass() != i.getClass()) return false;
		return this.getName() == i.getName() && this.getPrice() == i.getPrice() 
				&& this.getLevel() == i.getLevel();
	}

	//compareTo que organiza y elimina elementos repetidoz, el orden va de
	//TipoItem > OrdenAlfabetico > orden asendente de nivel > orden asendente de precio
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
