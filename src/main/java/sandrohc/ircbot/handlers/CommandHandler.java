package sandrohc.ircbot.handlers;

import sandrohc.ircbot.commands.Command;
import sandrohc.ircbot.commands.events.Event;
import sandrohc.ircbot.commands.events.EventAny;
import sandrohc.ircbot.commands.events.EventCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandHandler {
	public static CommandHandler INSTANCE;
	public static final String COMMAND_SUFFIX = ":";
	private List<Command> listeners = new ArrayList<>();

	public static enum EVENT_TYPE { COMMAND, ANY};

	public CommandHandler() {
		INSTANCE = this;
	}

	public void parse(String channel, String sender, String message) {
		fireEvent(new EventAny().setChannel(channel).setSender(sender).setMessage(message));

		if(message.startsWith(COMMAND_SUFFIX)) { // Check if it's a command
			if(message.length() == 1) return; // Stop any attempt to parse ":"

			String command;
			int index = message.indexOf(' ');
			if(index != -1) { // Check if we have any arguments for the specified command
				command = message.substring(1, index);	// Get the command name
				message = message.substring(index + 1);	// Get the command arguments
			} else {
				command = message.substring(1, message.length());
				message = ""; // Remove any garbage from here (try to execute with no arguments)
			}

			fireEvent(new EventCommand().setCommand(command).setChannel(channel).setSender(sender).setMessage(message));
		}
	}

	public Command get(String name) {
		if(name == null || name.isEmpty()) return null;

		for(Command command : listeners)
			if(command.isEqual(name)) return command;

		return null;
	}

	public boolean contains(String name) {
		return get(name) != null;
	}

	public Collection<Command> getListeners() {
		return listeners;
	}

	public void register(Command command) {
		listeners.add(command);
	}

	private void fireEvent(Event e) {
		LogHandler.debug("Firing event: " + e.toString());

		for(Command command : listeners)
			command.handleEvent(e);
	}
}
