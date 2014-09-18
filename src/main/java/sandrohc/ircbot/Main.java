package sandrohc.ircbot;

import sandrohc.ircbot.handlers.CommandHandler;

public class Main {
	public static void main(String[] args) {
		try {
			new Bot();
			new CommandHandler();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
