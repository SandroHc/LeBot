package sandrohc.ircbot.commands.events;

import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;

public class EventCommand extends Event {
	private String command = "";

	public String getCommand() {
		return command;
	}

	public EventCommand setCommand(String command) {
		this.command = command;
		return this;
	}

	@Override
	public EVENT_TYPE getType() {
		return EVENT_TYPE.COMMAND;
	}

	@Override
	public String toString() {
		return "EventCommand{" + "command='" + command + '\'' + "} " + super.toString();
	}
}