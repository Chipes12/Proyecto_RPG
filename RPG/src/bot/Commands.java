package bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {

	String prefix = "";
	
	public Commands(String prefix) {
		setPrefix(prefix);
		
	}
	
	private void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void onGuildMessageReceived (GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase(this.prefix + "help")) {
	      EmbedBuilder info = new EmbedBuilder();
	      info.setTitle("Welcome to your Epic RPG Adventure!");
	      info.setDescription("!help");
	      info.addField("Creators", "Chips & I", false);
	      info.setColor(0x04d7de);
	      event.getChannel().sendMessage(info.build()).queue();
	      info.clear();			
		}	
	}
}
