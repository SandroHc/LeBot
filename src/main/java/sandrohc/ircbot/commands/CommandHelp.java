package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.handlers.CommandHandler;

import java.io.IOException;
import java.util.Arrays;

public class CommandHelp extends Command {
	public CommandHelp() {
		this.setName("help");
		this.setDescription("Lista os comandos disponíveis. Para ajuda mais detalhada, usa " + getSuffix() + "help <comando>");
		this.setAliases(new String[]{ "?", "ajuda" });
	}

	@Override
	public boolean handleEvent(Event e) throws IOException {
		if(super.handleEvent(e)) {
			if(e.getMessage().length() == 0) return false; // Stop any attempt to parse ":"

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
			if(!isEqual(commandName)) return false;



			if(e.getMessage().isEmpty()) { // No arguments, show the whole command list
				StringBuilder sb = new StringBuilder();
				sb.append("Comandos: ");

				index = 0;
				for(Command command : CommandHandler.INSTANCE.getListeners()) {
					if(index > 0) sb.append(", ");
					sb.append(command.getName());

					if(command.getAliases().length > 0) sb.append(' ').append(Arrays.toString(command.getAliases()));

					index++;
				}
				Bot.INSTANCE.sendMessage(e.getChannel(), sb.toString());
			} else { // Arguments, show the help for the command
				Command command = CommandHandler.INSTANCE.get(e.getMessage());
				if(command != null) { // Command exists, show description
					if(command.getDescription().isEmpty()) Bot.INSTANCE.sendMessage(e.getChannel(), "Não existe descrição disponível para '" + e.getMessage() + "'.");
					else {
						String aliases = command.getAliases().length > 0 ? " " + Arrays.toString(command.getAliases()) : "";
						Bot.INSTANCE.sendMessage(e.getChannel(), command.getName() + aliases + ": " + command.getDescription());
					}
				} else {
					Bot.INSTANCE.sendMessage(e.getChannel(), "O comando '" + e.getMessage() + "' não existe. para uma lista dos comandos, escreve /help");
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}
}
