package sandrohc.ircbot.commands;

import sandrohc.ircbot.commands.events.Event;
import sandrohc.ircbot.commands.events.EventCommand;
import sandrohc.ircbot.handlers.CommandHandler;
import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;
import sandrohc.ircbot.handlers.LogHandler;

import java.util.Arrays;

public abstract class Command {
	private String name;
	private String description = "";
	private String[] aliases = {};

	protected Command() {
		CommandHandler.INSTANCE.register(this);
	}

	public abstract void handleEvent(Event e);
	public abstract void run(Event e);

	/**
	 * Check is the string belongs to the command on this event.
	 * It consists on check is any of the name or aliases is equal to the string.
	 *
	 * @param e The event to get the command
	 * @return true if the any of the name of alieases equals the string; false otherwise.
	 */
	public boolean isEqual(Event e) {
		return e instanceof EventCommand && isEqual(((EventCommand) e).getCommand());
	}

	/**
	 * Check is the string belongs to this command.
	 * It consists on check is any of the name or aliases is equal to the string.
	 *
	 * @param name The name to check against
	 * @return true if the any of the name of alieases equals the string; false otherwise.
	 */
	public boolean isEqual(String name) {
		if(this.getName().equals(name)) return true;
		for(String alias : aliases)
			if(alias.equals(name)) return true;

		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	public abstract EVENT_TYPE getType();

	protected void log(Object o) {
		LogHandler.info(getName() + ": " + o.toString());
	}

	@Override
	public String toString() {
		return "Command{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", aliases=" + Arrays.toString(aliases) +
				'}';
	}
}
