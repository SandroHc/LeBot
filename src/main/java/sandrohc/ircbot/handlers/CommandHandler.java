package sandrohc.ircbot.handlers;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.Command;
import sandrohc.ircbot.commands.CommandAdvanced;
import sandrohc.ircbot.commands.CommandHelp;
import sandrohc.ircbot.commands.CommandOsu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandHandler {
	public static CommandHandler INSTANCE;
	public static final String COMMAND_SUFFIX = ":";
	private List<Command> list = new ArrayList<>();

	public CommandHandler() {
		INSTANCE = this;
		init();
	}

	public void init() {
		list.clear(); // Used to clear all the elements on the list

		list.add(new CommandAdvanced());
		list.add(new CommandHelp());
		list.add(new CommandOsu());
	}

	public void parse(String channel, String sender, String message) {
		if(message.startsWith(COMMAND_SUFFIX)) { // Check if it's a command
			if(message.length() == 1) return; // Stop any attempt to parse ":"

			int index = message.indexOf(' ');
			if(index != -1) { // Check if we ave any arguments for the specified command
				String commandName = message.substring(1, index); // Get the command name
				message = message.substring(index + 1); // Get the command arguments

				Command command = get(commandName);
				if(command != null) // If the command was found, let him do it's thing, else present a error "unknown command"
					command.parse(channel, sender, message);
				else unknownCommand(channel, commandName);
			} else {
				String commandName = message.substring(1, message.length());
				Command command = get(commandName);
				if(command != null) command.parse(channel, sender, "");
				else unknownCommand(channel, commandName);
			}
		}
	}

	public Command get(String name) {
		if(name == null || name.isEmpty()) return null;

		for(Command command : list)
			if(command.isEqual(name)) return command;

		return null;
	}

	public Collection<Command> getList() {
		return list;
	}

	private void unknownCommand(String sendTo, String commandName) {
		Bot.INSTANCE.sendMessage(sendTo, "Comando '" + commandName + "' desconhecido. Usa " + CommandHandler.COMMAND_SUFFIX + "help para uma lista dos comandos disponíveis.");
	}
}
