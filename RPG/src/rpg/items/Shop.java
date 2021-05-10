package rpg.items;
import java.util.*;

public class Shop {
	//Atributo ser un arbol para no tener items repetidos
	private TreeSet<Item> items = new TreeSet<>();
	
	//Constructor
	public Shop () {
	}
	
	//Getter
	public TreeSet<Item> getItems() {
		return items;
	}
	
	//Setter se puede inicializar con un arbol el items
	public void setItems(TreeSet<Item> items) {
		this.items = items;
	}
	
	//Methods
	
	//Si se inicializa vacio se pueden agregar Items individualmente
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	//Borrar Items 
	public Item deleteItem(Item item) {
		try {
			items.remove(item);
			return item;
		}catch(Exception e) {
			return null;
		}
	}
	
	//Interfaz de menu de compras
	public String toString() {
		String str = "*******************Shop********************\t\n";
		List<Item> itemsList = new ArrayList<Item>(items);
		for(int i = 1; i <= items.size(); i++) {
			str += i +") " + itemsList.get(i-1).toString() + "\n";
		}
		return str;
	}
	
	//Selección y retorno de un item y en caso de que item no exista
	public Item selectItem(int index) { try {
		List<Item> itemsList = new ArrayList<Item>(items);
		return itemsList.get(index - 1);
	}catch(Exception e) {
		return null;
	}

	}
}
