package bot;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
	private String prefix;
	private String description;
	private static String BOT_TOKEN;
	
	public Bot(String prefix, String description) throws LoginException{

		setPrefix(prefix.replaceAll("\\s", ""));
	    
	    try{
	    	Dotenv dotenv = Dotenv.load();
	        BOT_TOKEN = dotenv.get("$BOT_TOKEN");
	    } catch (Exception e){
	        BOT_TOKEN = System.getenv("$BOT_TOKEN");
	    }
		    
	    JDA jda = JDABuilder.createDefault(BOT_TOKEN).build();
	    jda.getPresence().setActivity(Activity.playing(description));
	    jda.getPresence().setStatus(OnlineStatus.ONLINE);
	    jda.addEventListener(new Commands(prefix));
	}

  //Setters
	public void setPrefix(String prefix){
		if (prefix != null && prefix != "")
			this.prefix = prefix;
	}

	public void setDescription(String description){
		this.description = description;
	}

	//Getters
	public String getPrefix(){
		return this.prefix;
	}

	public String getDescription(){
		return this.description;
	}
	
}