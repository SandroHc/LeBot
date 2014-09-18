package sandrohc.ircbot;

import org.jibble.pircbot.PircBot;
import sandrohc.ircbot.handlers.CommandHandler;

public class Main extends PircBot {

	public static void main(String[] args) {
		try {
			new Bot();
			new CommandHandler();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
