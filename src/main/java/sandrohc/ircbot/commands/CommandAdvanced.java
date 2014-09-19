package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.events.Event;
import sandrohc.ircbot.commands.events.EventAny;
import sandrohc.ircbot.commands.events.EventCommand;
import sandrohc.ircbot.handlers.CommandHandler;
import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;

public class CommandAdvanced extends Command {
	private long lastResponse; // Used to prevent spam

	public CommandAdvanced() {
		this.setName("config");
		this.setDescription("Comandos avançados para controlar o bot.");
		this.setAliases(new String[]{ "adv" });
	}

	@Override
	public void handleEvent(Event e) {
		run(e);
	}

	@Override
	public void run(Event e) {
		if(e instanceof EventCommand) {
			EventCommand evtCmd = (EventCommand) e;
			if(!CommandHandler.INSTANCE.contains(evtCmd.getCommand())) // Send a unknown command warning
				Bot.INSTANCE.sendMessage(evtCmd.getChannel(), "Comando '" + evtCmd.getCommand() + "' desconhecido. Usa " + CommandHandler.COMMAND_SUFFIX + "help para uma lista dos comandos disponíveis.");
		} else if(e instanceof EventAny) {
			if(e.getMessage().contains("( ͡° ͜ʖ ͡°)") && lastResponse + 5000 < System.currentTimeMillis()) { // Prevent spam by making a minimum delay of 5 seconds
				Bot.INSTANCE.sendMessage(e.getChannel(), "( ͡° ͜ʖ ͡°)");
				lastResponse = System.currentTimeMillis();
			}
		}
	}

	@Override
	public EVENT_TYPE getType() {
		return EVENT_TYPE.ANY;
	}
}
