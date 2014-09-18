package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.handlers.CommandHandler;

public class CommandAdvanced extends Command {
	public CommandAdvanced() {
		this.setName("config");
		this.setDescription("Comandos avançados para controlar o bot.");
		this.setAliases(new String[]{ "adv" });
	}

	@Override
	public void parse(String channel, String sender, String message) {
		if(message.equals("reload")) {
			CommandHandler.INSTANCE.init();
			Bot.INSTANCE.sendMessage(channel, "Comandos recarregados com sucesso.");
		}
	}
}
