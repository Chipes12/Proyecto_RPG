package bot;

import java.io.IOException;
import javax.security.auth.login.LoginException;

public class RPGBot {
	public static Bot bot;

	public static void main(String[] args) throws IOException {
		
		try {
			bot = new Bot("!", "An RPG Bot! Use !help");
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
