package rpg.items;
import java.util.Map;
import java.util.TreeMap;

public class Bag {
	
	//Atributos static para usarse cuando se cree cualquier bag
	private static int BAG_SIZE = 30;
	private static int initialMoney = 20;
	private static TreeMap <Item, Integer> initialItems = new TreeMap<Item,Integer>(); 
	//items es un TreeMap para no tener items repetidos y asociarlos con la cantidad en la bolsa
	private TreeMap <Item, Integer> items = new TreeMap<Item,Integer>();
	private int money = initialMoney;
	//Constructor
	public Bag() {
		this.items.putAll(initialItems);
	}
	
	//Setters
	public void setItems(TreeMap <Item, Integer> items) {
		this.items = items;
	}

	public void setMoney(int money) {
		if (money >= 0) this.money = money;
	}
	//Setters static para que el usuario de la API pueda cambiar las variables iniciales
	public static void setBAG_SIZE(int bagSize) {
		if (Bag.BAG_SIZE <= bagSize) Bag.BAG_SIZE = bagSize;
	}
	
	public static void setInitialMoney(int initialMoney) {
		if (initialMoney >= 0) Bag.initialMoney = initialMoney;
	}
	
	public static void setInitialItems(TreeMap <Item, Integer> initialItems) {
		Bag.initialItems = initialItems;
	}
	//Getters
	public TreeMap <Item, Integer> getItems() {
		return items;
	}
	
	public int getMoney() {
		return money;
	}

	public static int getBAG_SIZE() {
		return BAG_SIZE;
	}

	public static int getInitialMoney() {
		return initialMoney;
	}

	public static TreeMap <Item, Integer> getInitialItems() {
		return initialItems;
	}
	
	//Methods
	//Regresa el item en el índice que identifica el usuario, transformando a un array de items del mismo tamaño
	public Item selectItem(int index) {
		try {
			return items.keySet().toArray(new Item[items.size()])[index];
		} catch (Exception e) {
			return null;
		}
	}
	//Añade item si hay espacio en la bolsa y si es nuevo. Si ya existe, le suma a su cantidad
	public boolean addItem(Item item) {
		int quantity = this.items.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
		if (quantity >= Bag.getBAG_SIZE()) return false;
		if (!this.items.containsKey(item))
			this.items.put(item, 1);
		else 
			this.items.put(item, items.get(item)+1);
		return true;
	}
	
	//Elimina item si este está dentro de la bolsa y solo hay uno de ellos. Si no, le resta 1 a la cantidad
	public Item deleteItem(Item item) {
		if (!this.items.containsKey(item)) return null;
		int quantity = items.get(item);
		if (quantity == 1) this.items.remove(item,1);
		else this.items.put(item, quantity - 1);
		return item;
	}
	//To string que recorre un TreeMap convertido en array, regresando ambos item y la cantidad de estos
	public String toString() {
		int quantity = this.items.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
		String str = "Capacity: "+ quantity + "/" + Bag.BAG_SIZE  +"\n\nGold: " 
					+ this.getMoney() + "\n\n";
		Item temp []= new Item[items.size()];
		Item item;
		for(int i = 0; i < this.items.size(); i++) {
			item = this.items.keySet().toArray(temp)[i];
			str += (i+1) +") " + item.toString() + "\t|Amount: " + this.items.get(item) + "\n";
		}
		return str;
	}
}
