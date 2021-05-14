package bot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import rpg.RPG;


public class Bot {
    private String description;
    private JDA jda;
    public static RPG<Long> juego;
    private Commands commands;
    //private static String BOT_TOKEN;

    public Bot(String prefix, String description, RPG<Long> juego) throws LoginException{
    	
        setJDA();
        set(juego);
        this.commands = new Commands(this,prefix);
        this.jda.addEventListener(commands);
        setDescription(description + " Use " + commands.getPrefix() +"help");
    }

//Setters
    public void setDescription(String description){
        this.description = description;
        this.jda.getPresence().setActivity(Activity.playing(this.description));
    }

    private void setJDA() throws LoginException{
        JDA jda = JDABuilder.createDefault("").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        this.jda=jda;
    }

    private void set(RPG<Long> juego) {
		Bot.juego = juego;
	}
    //Getters
    public String getDescription(){
        return this.description;
    }
    public JDA getJDA(){
        return this.jda;
    }
    public RPG<Long> getRPG(){
        return Bot.juego;
    }


}