package bot;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

	String prefix = "!";
	Bot bot;
	List<String> bConfig = Arrays.asList("help", "prefix <new-prefix>", "yee");
	
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
	//Returns specified command in the list
	public String arg (List<String> l, int row, int column) {
		return (l.get(row).split(" ")[column]).toString();
	}
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		//!help
		if (args[0].equalsIgnoreCase(this.prefix + arg(bConfig,0,0))) {
	      EmbedBuilder info = new EmbedBuilder();
	      info.setTitle("**Welcome to your Epic RPG Adventure!**");
	      //Commands
	      String msg= "Add `" + this.prefix + "` before any command :^)\n\n:tools: **Config Commands** :tools:\n";
	      for (String s: bConfig) msg += "`" + s + "` ";
	      msg += "\n\n:man_mage: **Character Commands** :man_mage:\n\n";
	      msg += "\n\n:crystal_ball: **Shopping Commands** :crystal_ball:\n\n";
	      msg += "\n\n:crossed_swords: **Fighting Commands** :crossed_swords:\n\n";
	      
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
		  
		
	}
}
