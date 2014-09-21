package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;

public class CommandAdvanced extends Command {
	private long[] responses = { 0, 0, 0 }; // smile response, oyasumi response, ohayo response

	@Override
	protected void init() {
		this.setName("config");
		this.setDescription("Comandos avançados para controlar o bot.");
		this.setAliases(new String[]{ "adv" });
	}

	@Override
	protected boolean validate(Event e) {
		if(!super.validate(e)) return false;

		if(e.getMessage().length() == 0) return false; // Stop any attempt to parse empty (no arguments) events

		String commandName;
		int index = e.getMessage().indexOf(' ');
		if(index != -1) { // Check if we have any arguments for the specified command
			commandName = e.getMessage().substring(0, index);    // Get the command name
			e.setMessage(e.getMessage().substring(index + 1));    // Get the command arguments
		} else {
			commandName = e.getMessage().substring(0, e.getMessage().length());
			e.setMessage(""); // Remove any garbage from here (try to execute with no arguments)
		}

		// Not this command, return
		return isEqual(commandName);
	}

	@Override
	protected void execute(Event e) {
		Bot.INSTANCE.sendMessage(e.getChannel(), "Yep. És operador, parabéns!");
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}

	@Override
	public String getUse() {
		return getSuffix() + getName();
	}

	@Override
	public String getExampleUse() {
		return "";
	}

	@Override
	public boolean onlyOps() {
		return true;
	}
}
