package rpg.items;
import java.util.TreeMap;

public class Bag {
	//items es un TreeMap para no tener items repetidos y asociarlos con la cantidad en la bolsa
	private TreeMap <Item, Integer> items = new TreeMap<Item,Integer>();
	private int money;
	//Atributos static para usarse cuando se cree cualquier bag
	private static int BAG_SIZE = 30;
	private static int initialMoney = 100;
	private static TreeMap <Item, Integer> initialItems; 
	
	//Constructor
	public Bag() {}
	
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
		if (this.items.size() >= Bag.getBAG_SIZE()) return false;
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
		String str = "";
		Item temp []= new Item[items.size()];
		Item item;
		for(int i = 1; i <= this.items.size(); i++) {
			item = this.items.keySet().toArray(temp)[i];
			str += i +") " + item.toString() + "\tAmount: " + this.items.get(item) + "\n";
		}
		return str;
	}
}
