package bot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;


public class Bot {
    private String description;
    private JDA jda;
    private Commands commands;
    //private static String BOT_TOKEN;



    public Bot(String prefix, String description) throws LoginException{

        setJDA();
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

    //Getters
    public String getDescription(){
        return this.description;
    }
    public JDA getJDA(){
        return this.jda;
    }

}