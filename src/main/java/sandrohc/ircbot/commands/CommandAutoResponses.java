package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;

public class CommandAutoResponses extends Command {
	private long[] responses = { 0, 0, 0 }; // smile response, oyasumi response, ohayo response

	@Override
	protected void init() {
		this.setName("auto_response");
		this.setDescription("Módulo de respostas automáticas.");
	}

	@Override
	protected void execute(Event e) {
		if((e.getMessage().contains("puta")) || e.getMessage().contains("cabrão")) {
			Bot.INSTANCE.sendMessage(e.getChannel(), "Korosu yo!");
			return;
		}

		if(e.getMessage().contains("ohayo") || e.getMessage().contains("konnichiwa") || e.getMessage().contains("konbanwa") || e.getMessage().contains("olá")) {
			if(responses[2] + 10000 < System.currentTimeMillis()) {
				Bot.INSTANCE.sendMessage(e.getChannel(), "Ohayo " + e.getSender() + "-kun!");
				responses[2] = System.currentTimeMillis();
			}
		} else if(e.getMessage().contains("oyasumi")) {
			if(responses[1] + 10000 < System.currentTimeMillis()) {
				Bot.INSTANCE.sendMessage(e.getChannel(), "Oyasumi! matta ashita " + e.getSender() + "-kun.");
				responses[1] = System.currentTimeMillis();
			}
		} else if(e.getMessage().contains(Bot.INSTANCE.getName())) {
			String msg = e.getSender().equalsIgnoreCase("SandroHc") ? "Sandro-kun, ore no daisuki no otoko, nani? :heart:" : "nani " + e.getSender() + "-kun?";
			Bot.INSTANCE.sendMessage(e.getChannel(), msg);
		}

		if(e.getMessage().contains("( ͡° ͜ʖ ͡°)") && responses[0] + 5000 < System.currentTimeMillis()) { // Prevent spam by making a minimum delay of 5 seconds
			Bot.INSTANCE.sendMessage(e.getChannel(), "( ͡° ͜ʖ ͡°)");
			responses[0] = System.currentTimeMillis();
		}
	}

	@Override
	public boolean hasSuffix() {
		return false;
	}

	@Override
	public String getUse() {
		return "";
	}

	@Override
	public String getExampleUse() {
		return "";
	}

	@Override
	public boolean onlyOps() {
		return false;
	}
}
