package sandrohc.ircbot.handlers;

import sandrohc.ircbot.commands.Command;
import sandrohc.ircbot.commands.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandHandler {
	public static CommandHandler INSTANCE;
	private List<Command> listeners = new ArrayList<>();

	public CommandHandler() {
		INSTANCE = this;
	}

	public void parse(String channel, String sender, String message) {
		if(message.isEmpty() || message.startsWith(" ")) return;

		fireEvent(channel, sender, message);
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

	private void fireEvent(String channel, String sender, String message) {
//		Event event = new Event().setChannel(channel).setSender(sender).setMessage(message);

		for(Command command : listeners)
			try {
				command.handleEvent(new Event().setChannel(channel).setSender(sender).setMessage(message));
			} catch(IOException e) {
				e.printStackTrace();
			}
	}
}
