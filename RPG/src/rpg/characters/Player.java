package rpg.characters;
import rpg.items.Item;
import rpg.items.Bag;

public class Player {
	private Bag bag = new Bag();
	private static double sellRate = 0.80;
	private static int minToSellRate = 20;  
	
	public Item sellItem(Item item) {
		if (!bag.getItems().containsKey(item)) return null;
		int money = item.getPrice(); 
		if (money >= Player.minToSellRate) money = (int) (money*Player.sellRate);   
		bag.deleteItem(item);
		bag.setMoney(bag.getMoney() + money);
		return item;
	}

	public static double getSellRate() {
		return sellRate;
	}

	public static void setSellRate(double sellRate) {
		if (sellRate > 0 && sellRate < 1) Player.sellRate = sellRate;
	}

	public static int getMinToSellRate() {
		return minToSellRate;
	}

	public static void setMinToSellRate(int minToSellRate) {
		Player.minToSellRate = minToSellRate;
	}
	
}
