package bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rpg.characters.Player;
import rpg.characters.Stats;
import rpg.items.Consumable;
import rpg.items.Item;
import rpg.specialities.PlayerClass;

public class Commands extends ListenerAdapter {

	String prefix = "!";
	Bot bot;
	List<String> guilds = new ArrayList<String> ();
	TreeMap <Long, Player> users = new TreeMap <Long, Player>();
	List<String> bConfig = Arrays.asList("help", "prefix <new-prefix>", "join game", "get players");
	List<String> characterC = Arrays.asList("info", "bag" ,"use <item-bag-index>", "equip <item-bag-index>", "unequip", "level <stat> <points>", "classes","level <skill-id> <points>");
	List<String> shoppingC = Arrays.asList("shop", "buy <item-index> <quantity>", "sell <item-bag-index>");
	List<String> fightingC = Arrays.asList("fight");
	
	public Commands(Bot bot, String prefix) {
		setPrefix(prefix);
		setBot(bot);
	}
	//Setter
	private boolean setPrefix(String prefix) {
		if (prefix == null || prefix == "" ||  Character.isLetterOrDigit(prefix.charAt(prefix.length()-1)) || prefix.length()>4)
			return false;
		this.prefix = prefix;
		return true;
	}
	
	private void setBot(Bot bot) {
		if (bot != null)
			this.bot = bot;
	}
	//Getter
	public String getPrefix(){
		return this.prefix;
	}
	//Regresa comando especificado en la lista
	public String arg (List<String> l, int row, int column) {
		return (l.get(row).split(" ")[column]).toString();
	}

	public void onGuildMessageReceived (GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		Long userID = event.getAuthor().getIdLong();
		
		//!help
		if (args[0].equalsIgnoreCase(this.prefix + arg(bConfig,0,0))) {
	      EmbedBuilder info = new EmbedBuilder();
	      info.setTitle("**Welcome to your Epic RPG Adventure!**");
	      //Commands Menu
	      String msg= "Add `" + this.prefix + "` before any command :^)\n\n:tools: **Config Commands** :tools:\n";
	      for (String s: bConfig) msg += "`" + s + "` ";
	      msg += "\n\n:man_mage: **Character Commands** :man_mage:\n\n";
	      for (String s: characterC) msg += "`" + s + "` ";
	      if (this.bot.getRPG().getPlayers().containsKey(userID))
	    		  if (this.bot.getRPG().getPlayers().get(userID).getPlayerClass() == null) msg += "`choose class` ";
	      msg += "\n\n:crystal_ball: **Shopping Commands** :crystal_ball:\n\n";
	      for (String s: shoppingC) msg += "`" + s + "` ";
	      msg += "\n\n:crossed_swords: **Fighting Commands** :crossed_swords:\n\n";
	      for (String s: fightingC) msg += "`" + s + "` ";
	      
	      info.setDescription(msg + "\n");
	      
	      info.addField("Creators", "Carlos A. & Sofía", true);
	      info.setColor(0x04d7de);
	      event.getChannel().sendMessage(info.build()).queue();
	      info.clear();	
	      
		}
		//!prefix <new-prefix>
		else if (args[0].equalsIgnoreCase(this.prefix + arg(bConfig,1,0))) {		
		  if(!setPrefix(args[1]))
			  event.getChannel().sendMessage("Should be `" + prefix + bConfig.get(1) + "` (max 4 chars, not ending in letter/number)").queue();
		  else {
			  String d = bot.getDescription();
		      bot.setDescription(d.substring(0, d.lastIndexOf(" ")) + " " + this.prefix + "help");
		      event.getChannel().sendMessage("Prefix is now " + this.prefix).queue();
		  }  
		}
		//!join game
		else if (args[0].equalsIgnoreCase(this.prefix + arg(bConfig,2,0)) && args[1].equalsIgnoreCase(arg(bConfig,2,1))) {
			if (this.bot.getRPG().getPlayers().containsKey(userID)) {
				event.getChannel().sendMessage("Already playing.").queue();
				return;
			}
			this.bot.getRPG().addPlayer(userID, event.getAuthor().getName());

			event.getChannel().sendMessage("¡Bienvenid@ "+ event.getAuthor().getName() + "!").queue();
		}
		//!get players
		else if (args[0].equalsIgnoreCase(this.prefix + arg(bConfig,3,0)) && args[1].equalsIgnoreCase(arg(bConfig,3,1))) {
			if (this.bot.getRPG().getPlayers().size() == 0) return;
			event.getChannel().sendMessage(this.bot.getRPG().toStringPlayers()).queue();
		}
		//Sólo para jugadores
		else if (this.bot.getRPG().getPlayers().containsKey(userID)) {
			User user = this.bot.getJDA().getUserById(userID);
			Player p = this.bot.getRPG().getPlayers().get(userID);
			//Character Commands
			//!info
			if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,0,0))) {
			      EmbedBuilder info = new EmbedBuilder();
			      info.setTitle("Perfil de " + p.getName());
			      info.setDescription(p.toStringDetails() + "\nStat Points: " + p.getStatPoints());
			      info.setColor(0x04d7de);
			      event.getChannel().sendMessage(info.build()).queue();
			      info.clear();	
			}
			//!bag
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,1,0))) {
			      EmbedBuilder info = new EmbedBuilder();
			      info.setTitle("Bolsa de " + user.getName());
			      info.setDescription(p.getBag().toString() + "\n");
			      info.setColor(0x04d7de);
			      event.getChannel().sendMessage(info.build()).queue();
			      info.clear();	
			}
			//!use <item_bag_index>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,2,0))) {
				try {
					Integer.parseInt(args[1]);
				} catch(Exception ex) {
					event.getChannel().sendMessage("Indica el número de item en tu bolsa `use <item_bag_index>`").queue();
					return;
				}
				int q = Integer.parseInt(args[1]) - 1;
				Item item = p.getBag().selectItem(q);
				if (item != null) {
					if (!item.getItemType().equals("Consumable")) {
						event.getChannel().sendMessage("No puedes usar este item, equípalo").queue();
						return;
					}
					if (p.useConsumable((Consumable)item)) {
						event.getChannel().sendMessage("Has usado:\n\n" + item.toStringDetails()).queue();
						p.getBag().deleteItem(item);
						((Consumable)item).boostStasts(p);
					}
					return;
				}   
				event.getChannel().sendMessage("No tienes este item en tu bolsa").queue();
			}
			//!equip <item-bag-index>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,3,0))) {
				try {
					Integer.parseInt(args[1]);
				} catch(Exception ex) {
					event.getChannel().sendMessage("Indica el número de item en tu bolsa `equip <item_bag_index>`").queue();
					return;
				}
				int q = Integer.parseInt(args[1]) - 1;
				Item item = p.getBag().selectItem(q);
				
				if (item != null) {
					
					if (item.getItemType().equals("Consumable")) {
						event.getChannel().sendMessage("No puedes equipar este Item, úsalo").queue();
						return;
					}
					if (p.equipItem(item)) {
						event.getChannel().sendMessage("Has equipado:\n" + item.toStringDetails()).queue();
					}
					return;
				}  
				event.getChannel().sendMessage("El item no está en tu bolsa").queue();
			}
			//!unequip
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,4,0))) {
				Item item= p.unequipItem();
				if (item != null) 
					event.getChannel().sendMessage("Has inequipado \"" + item.getName()+ "\"").queue();
				else
					event.getChannel().sendMessage("Error inequipando item").queue();    
			}
			//!level <stat> <stat-points>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,5,0))) {
				try {
					args[1].toLowerCase();
					Stats.valueOf(args[1]);
					Integer.parseInt(args[2]);
				} catch(Exception ex) {
					event.getChannel().sendMessage("Indica el stat más los stat points `level <stat> <stat-points>`").queue();
					return;
				}
				Stats stat = Stats.valueOf(args[1]);
				if (stat == Stats.max_hp || stat == Stats.max_mp) {
					event.getChannel().sendMessage("No puedes lvlear max_hp/max_mp").queue();
					return;
				};
				int points = Integer.parseInt(args[2]);
				
				if (p.getStatPoints() - points < 0 || points == 0) {
					event.getChannel().sendMessage("No tienes suficientes puntos").queue();
					return;
				}
				
				for (int i=0; i<points; i++) {
					p.levelStat(stat);
					p.setStatPoints(p.getStatPoints() - 1);
				}
				event.getChannel().sendMessage("Leveleaste: " + stat).queue();
			}
			//!classes
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,6,0))) {
				  EmbedBuilder info = new EmbedBuilder();
			      info.setTitle("Clases");
			      info.setDescription(this.bot.getRPG().toStringClasses() + "\n");
			      info.setColor(0x04d7de);
			      event.getChannel().sendMessage(info.build()).queue();
			      info.clear();	
			}
			//!choose class <class>
			else if (args[0].equalsIgnoreCase(this.prefix + "choose class")) {
                try {
                    Integer.parseInt(args[1]);
                } catch(Exception ex) {
                    event.getChannel().sendMessage("Qué clase quieres `choose class <Class_id>`").queue();
                    return;
                }
                int q = Integer.parseInt(args[1]) - 1;
                int j = 0;
                PlayerClass[] classes = new PlayerClass[this.bot.getRPG().getClasses().size()];
                for(PlayerClass i: this.bot.getRPG().getClasses())
                    classes[j] = i;
                PlayerClass pc = classes[q];

                if (pc != null) {

                    if (p.chooseClass(pc)) {
                        event.getChannel().sendMessage("Ahora eres un :\n" + pc.getName()).queue();
                    }
                    return;
                }
                event.getChannel().sendMessage("Nuevos detalles " + pc.toString()).queue();
            }
			//!level <skill-id> <points>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(characterC,7,0))) {
		
			}
			
			//SHOPPING
			
			//!shop
			else if (args[0].equalsIgnoreCase(this.prefix + arg(shoppingC,0,0))) {
				EmbedBuilder info = new EmbedBuilder();
			    info.setTitle("SHOP");
			    info.setDescription(this.bot.getRPG().getShop().toString() + "\n");
			    info.setColor(0x04d7de);
			    event.getChannel().sendMessage(info.build()).queue();
			    info.clear();	
			}
			//!buy <item-index> <quantity>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(shoppingC,1,0))) {
				try {
					Integer.parseInt(args[1]);
					Integer.parseInt(args[2]);
				} catch(Exception ex) {
					event.getChannel().sendMessage("Indica el número de item en la tienda `buy <item-index> <quantity>`").queue();
					return;
				}
				int index = Integer.parseInt(args[1])-1;
				int q = Integer.parseInt(args[2]);
				Item item = this.bot.getRPG().getShop().selectItem(index);
				if (item != null) {
					int bought = p.buyItem(item, q);
					if (bought == 0) {
						event.getChannel().sendMessage("No se pudo comprar el item o no tienes espacio en tu bolsa").queue();
						return;
					}
					event.getChannel().sendMessage("Has comprado "+ bought + " \"" + item.getName()+ "\"").queue();
					return;
				} 
				event.getChannel().sendMessage("No existe este item").queue();
			}
			//!sell <item-index>
			else if (args[0].equalsIgnoreCase(this.prefix + arg(shoppingC,2,0))) {
                try {
                    Integer.parseInt(args[1]);
                } catch(Exception ex) {
                    event.getChannel().sendMessage("Indica el número de item en tu bolsa sell <item_bag_index>").queue();
                    return;
                }
                int q = Integer.parseInt(args[1]) - 1;
                Item item = p.getBag().selectItem(q);

                if (item != null) {

                    if (p.sellItem(item)) {
                        event.getChannel().sendMessage("Has Vendido:\n" + item.toStringDetails()).queue();
                    }
                    return;
                }
                event.getChannel().sendMessage("Nuevo saldo" + p.getBag().toString()+q).queue();
            }
			//FIGHTING

		} else if (args[0].contains(prefix)) {
			 event.getChannel().sendMessage("Usa !join game para jugar").queue();
		}

	}
}
