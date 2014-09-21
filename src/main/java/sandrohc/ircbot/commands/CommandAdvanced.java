package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;

import java.io.IOException;

public class CommandAdvanced extends Command {
	private long[] responses = { 0, 0, 0 }; // smile response, oyasumi response, ohayo response

	public CommandAdvanced() {
		this.setName("config");
		this.setDescription("Comandos avançados para controlar o bot.");
		this.setAliases(new String[]{ "adv" });
	}

	@Override
	public boolean handleEvent(Event e) throws IOException {
		if(super.handleEvent(e)) {
//			EventCommand evtCmd = (EventCommand) e;
//			if(!CommandHandler.INSTANCE.contains(evtCmd.getCommand())) // Send a unknown command warning
//				Bot.INSTANCE.sendMessage(evtCmd.getChannel(), "Comando '" + evtCmd.getCommand() + "' desconhecido. Usa " + getSuffix() + "help para uma lista dos comandos disponíveis.");

			if((e.getMessage().contains("puta")) || e.getMessage().contains("cabrão")) {
				Bot.INSTANCE.sendMessage(e.getChannel(), "korosu yo!");
				return true;
			}

			if(e.getMessage().contains("ohayo") || e.getMessage().contains("konnichiwa") || e.getMessage().contains("konbanwa") || e.getMessage().contains("olá")) {
				if(responses[2] + 10000 < System.currentTimeMillis()) {
					Bot.INSTANCE.sendMessage(e.getChannel(), "ohayo " + e.getSender() + "-kun!");
					responses[2] = System.currentTimeMillis();
				}
			} else if(e.getMessage().contains("oyasumi")) {
				if(responses[1] + 10000 < System.currentTimeMillis()) {
					Bot.INSTANCE.sendMessage(e.getChannel(), "oyasumi! matta ashita " + e.getSender() + "-kun.");
					responses[1] = System.currentTimeMillis();
				}
			} else if(e.getMessage().contains(Bot.INSTANCE.getName())) {
				String msg = e.getSender().equals("SandroHc") ? "Sandro-kun, ore no daisuki no otoko, nani? ♥" : "nani " + e.getSender() + "-kun?";
				Bot.INSTANCE.sendMessage(e.getChannel(), msg);
			}

			if(e.getMessage().contains("( ͡° ͜ʖ ͡°)") && responses[0] + 5000 < System.currentTimeMillis()) { // Prevent spam by making a minimum delay of 5 seconds
				Bot.INSTANCE.sendMessage(e.getChannel(), "( ͡° ͜ʖ ͡°)");
				responses[0] = System.currentTimeMillis();
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean hasSuffix() {
		return false;
	}
}
