package sandrohc.ircbot.commands;

public abstract class Command {
	private String name;
	private String description = "";
	private String[] aliases = {};

	public abstract void parse(String channel, String sender, String message);

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
}
