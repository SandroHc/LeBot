package sandrohc.ircbot;

import sandrohc.ircbot.commands.CommandAdvanced;
import sandrohc.ircbot.commands.CommandAutoResponses;
import sandrohc.ircbot.commands.CommandCalculator;
import sandrohc.ircbot.commands.CommandGoogle;
import sandrohc.ircbot.commands.CommandHelp;
import sandrohc.ircbot.commands.CommandOsu;
import sandrohc.ircbot.handlers.CommandHandler;

public class Main {
	public static void main(String[] args) {
		try {
			// Initialize the bot
			new Bot();

			// Initialize the command handler
			new CommandHandler();

			// Load the commands
			new CommandHelp();
			new CommandAutoResponses();
			new CommandAdvanced();
			new CommandGoogle();
			new CommandCalculator();
			new CommandOsu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
