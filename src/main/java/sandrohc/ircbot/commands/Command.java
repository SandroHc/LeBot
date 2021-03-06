package sandrohc.ircbot.commands;

import org.jibble.pircbot.User;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.handlers.CommandHandler;
import sandrohc.ircbot.handlers.LogHandler;

import java.util.Arrays;

public abstract class Command {
	private String name;
	private String description = "";
	private String[] aliases = {};

	protected Command() {
		init();
		CommandHandler.INSTANCE.register(this);
	}

	protected abstract void init();

	public void handleEvent(Event e) {
		if(validate(e))
			execute(e);
	}

	protected boolean validate(Event e) {
		if(hasSuffix()) {
			if(getSuffix().equals(String.valueOf(e.getMessage().charAt(0)))) {
				if(onlyOps() && !isOp(e.getChannel(), e.getSender())) return false; // Check only for operator status when truly needed

				e.setMessage(e.getMessage().substring(1));
			}
			else
				return false;
		}
		return !(onlyOps() && !isOp(e.getChannel(), e.getSender())); // Check only for operator status when truly needed
	}

	protected abstract void execute(Event e);

	/**
	 * Check is the string belongs to this command.
	 * It consists on check is any of the name or aliases is equal to the string.
	 *
	 * @param name The name to check against
	 * @return true if the any of the name of alieases equals the string; false otherwise.
	 */
	public boolean isEqual(String name) {
		if(this.getName().equals(name)) return true;
		for(String alias : aliases) if(alias.equals(name)) return true;
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

	public abstract boolean hasSuffix();

	/**
	 * Return the suffix needed to proceed in this comand.
	 * Default suffix is '!'.
	 *
	 * @return The specific suffix for this command
	 */
	public String getSuffix() { return "!"; }

	public abstract String getUse();
	public abstract String getExampleUse();

	public abstract boolean onlyOps();

	public boolean isOp(String channel, String nick) {
		if(nick == null || nick.isEmpty()) return false;

		for(User user : Bot.INSTANCE.getUsers(channel))
			if(user.getNick().equals(nick)) return user.isOp();
		return false;
	}

	protected void log(Object o) {
		LogHandler.info(getName() + ": " + o.toString());
	}

	@Override
	public String toString() {
		return "Command{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", aliases=" + Arrays.toString(aliases) + ", hasSuffix=" + hasSuffix() + (hasSuffix() ? ", getSuffix='" + getSuffix() + "'" : "") + "}";
	}
}
