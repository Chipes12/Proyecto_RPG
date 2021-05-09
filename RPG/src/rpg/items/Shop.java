package rpg.items;
import java.util.*;

public class Shop {

	private TreeSet<Item> items;
	
	//Constructor
	public Shop () {
	}
	//Getter
	public TreeSet<Item> getItems() {
		return items;
	}
	//Setter
	public void setItems(TreeSet<Item> items) {
		this.items = items;
	}
	//Methods
	public void addItem(Item item) {
		this.items.add(item);
	}
	public void deleteItem(Item item) {
		this.items.remove(item);
	}
	public String toString() {
		String str = "\t\t\tShop\t\t\t\n";
		List<Item> itemsList = new ArrayList<Item>(items);
		for(int i = 0; i < items.size(); i++) {
			str += i +") " + itemsList.get(i).toString();
		}
		return str;
	}
}
