package bot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.security.auth.login.LoginException;

import rpg.*;
import rpg.characters.Enemy;
import rpg.characters.Player;
import rpg.characters.Stats;
import rpg.items.Armor;
import rpg.items.Consumable;
import rpg.items.Item;
import rpg.items.Shop;
import rpg.items.Weapon;
import rpg.items.Weapon.WeaponEnum;
import rpg.specialities.PlayerClass;
import rpg.specialities.Skill;
import rpg.specialities.Skill.SkillEnum;

public class RPGBot {
	public static Bot bot;
	public static void main(String[] args) throws IOException {


        try {
            bot = new Bot("!", "An RPG Bot!");
        } catch (LoginException e) {
            e.printStackTrace();
        }

		
		
		RPG<Long> juego = new RPG<Long>() {
		};
		//Habilidades
		Skill sk1_1 = new Skill("Corte", 10, 0, 5, 1, true, WeaponEnum.MELEE, SkillEnum.ATTACK);
		Skill sk1_2 = new Skill("Golpe Duro", 20, 0, 15, 1, true, WeaponEnum.MELEE, SkillEnum.ATTACK);
		Skill sk1_3 = new Skill("Cortes Rapidos", 15, 0, 10, 2, true, WeaponEnum.MELEE, SkillEnum.ATTACK);
		
		Skill sk2_1 = new Skill("Chispa", 0, 10, 5, 1, true, WeaponEnum.MAGICRANGE, SkillEnum.ATTACK);
		Skill sk2_2 = new Skill("Chorro de agua", 0, 5, 0, 1, true, WeaponEnum.MAGICRANGE, SkillEnum.ATTACK);
		Skill sk2_3 = new Skill("Golpe de viento", 0, 20, 20, 3, true, WeaponEnum.MAGICRANGE, SkillEnum.ATTACK);
		
		
		TreeSet<Skill> EspadachinHabilidades = new TreeSet<>();
		EspadachinHabilidades.add(sk1_1);
		EspadachinHabilidades.add(sk1_2);
		EspadachinHabilidades.add(sk1_3);
		
		TreeSet<Skill> MagoHabilidades = new TreeSet<>();
		MagoHabilidades.add(sk2_1);
		MagoHabilidades.add(sk2_2);
		MagoHabilidades.add(sk2_3);
		
		//Estadisticas
		TreeMap<Stats, Integer> EspadachinStats = new TreeMap<>();
		EspadachinStats.put(Stats.max_hp, 100);
		EspadachinStats.put(Stats.max_mp, 100);
		EspadachinStats.put(Stats.strength, 30);
		EspadachinStats.put(Stats.intelligence, 20);
		EspadachinStats.put(Stats.dexterity, 40);
		EspadachinStats.put(Stats.defense, 20);
		EspadachinStats.put(Stats.constitution, 10);
		
		TreeMap<Stats, Integer> MagoStats = new TreeMap<>();
		MagoStats.put(Stats.max_hp, 100);
		MagoStats.put(Stats.max_mp, 100);
		MagoStats.put(Stats.strength, 20);
		MagoStats.put(Stats.intelligence, 50);
		MagoStats.put(Stats.dexterity, 40);
		MagoStats.put(Stats.defense, 20);
		MagoStats.put(Stats.constitution, 10);
		
		PlayerClass Espadachin = new PlayerClass("Espadachin", MagoHabilidades, MagoStats);
		PlayerClass Mago = new PlayerClass("Mago", MagoHabilidades, MagoStats);
		
		juego.addClass(Mago);
		juego.addClass(Espadachin);
		
		//Drops
		Item i1 = new Item("Baba de slime", 1, 1);
		Item i2 = new Item("Hueso", 5, 1);
		Item i3 = new Item("Piedra dura", 10, 2);
		Item i4 = new Item("Lata de basura", 5, 1);
		Item i5 = new Item("Manzana", 20, 5);
		
		//Armas
		Weapon w1 = new Weapon("Espada de madera", 5, 1, 5, 0, 0, WeaponEnum.MELEE);
		Weapon w2 = new Weapon("Espada de piedra", 6, 3, 10, 0, 0, WeaponEnum.MELEE);
		Weapon w3 = new Weapon("Espada larga", 30, 5, 20, 0, 0, WeaponEnum.MELEE);
		
		Weapon w4 = new Weapon("Rama de madera", 5, 1, 0, 5, 0, WeaponEnum.MAGICRANGE);
		Weapon w5 = new Weapon("Baston antiguo", 6, 3, 0, 10, 0, WeaponEnum.MAGICRANGE);
		Weapon w6 = new Weapon("Baculo magico", 30, 5, 0, 20, 0, WeaponEnum.MAGICRANGE);
		
		//Armaduras
		Armor a1 = new Armor("Abrigo de cuero", 5, 1, 5);
		Armor a2 = new Armor("Tunica Larga", 20, 2, 10);
		Armor a3 = new Armor("Armadura simple", 40, 5, 25);
		Armor a4 = new Armor("Armadura de acero", 100, 10, 50);
		
		//Pociones
		Consumable c1 = new Consumable("Pocion de vida", 10, 1, 10, 0,0,0,0,0,0);
		Consumable c2 = new Consumable("Pocion de fuerza", 20, 5, 0, 0,10,0,0,0,0);
		Consumable c3 = new Consumable("Pocion de defensa", 10, 1, 10, 0,0,0,0,0,10);
		
		juego.addItem(i1);
		juego.addItem(i2);
		juego.addItem(i3);
		juego.addItem(i4);
		juego.addItem(i5);
		juego.addItem(w1);
		juego.addItem(w2);
		juego.addItem(w3);
		juego.addItem(w4);
		juego.addItem(w5);
		juego.addItem(w6);
		juego.addItem(a1);
		juego.addItem(a2);
		juego.addItem(a3);
		juego.addItem(a4);
		juego.addItem(c1);
		juego.addItem(c2);
		juego.addItem(c3);
		
		//Dropeos
		List<Item> slimeDrop = new ArrayList<>();
		slimeDrop.add(i1);
		List<Item> skeletonDrop = new ArrayList<>();
		skeletonDrop.add(i2);
		List<Item> goblinDrop = new ArrayList<>();
		goblinDrop.add(i3);
		goblinDrop.add(i4);
		
		//Enemigos
		juego.addEnemy("Goblin", 3, goblinDrop, 10, 10);
		juego.addEnemy("Skeleton", 2, skeletonDrop, 5, 5);
		juego.addEnemy("Slime", 1, slimeDrop, 1, 1);
		
		//Jugadores
		Player p1 = new Player("Chipes");
		Player p2 = new Player("Gyuunto");
		
		juego.addPlayer(p1,1L);
		juego.addPlayer(p2,2L);
		
		//Tienda
		Shop shop = new Shop(juego);
		shop.addItem(w1);
		shop.addItem(w2);
		shop.addItem(w3);
		shop.addItem(w4);
		shop.addItem(w5);
		shop.addItem(w6);
		shop.addItem(a1);
		shop.addItem(a2);
		shop.addItem(c1);
		shop.addItem(c2);
		shop.addItem(c3);
		
		List <Enemy> enc1Enemies = new ArrayList <Enemy>();
        enc1Enemies.add(juego.getEnemies().get(2));
        enc1Enemies.add(juego.getEnemies().get(2).clone());
        enc1Enemies.add(juego.getEnemies().get(2).clone());

        //Stream.generate(ArrayList<Enemy>::new).limit(10).collect(Collectors.toList());

        juego.addEncounter(enc1Enemies);

        System.out.println("\n#####SHOP#####\n");
        System.out.println(juego.getShop().toString());

        System.out.println("\n#####ITEMS#####\n");
        System.out.println(juego.toStringItems());

        System.out.println("\n#####PLAYERS#####\n");
        System.out.println(juego.toStringPlayers());

        System.out.println("\n#####ENEMIES#####\n");
        System.out.println(juego.toStringEnemies());

        System.out.println("\n#####CLASSES#####\n");
        System.out.println(juego.toStringClasses());

        System.out.println("\n#####ENCOUNTERS#####\n");
        System.out.println(juego.toStringEncounters());
		
	}
}
