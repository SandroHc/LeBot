package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.events.Event;
import sandrohc.ircbot.handlers.CommandHandler;
import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;

import java.util.Arrays;

public class CommandHelp extends Command {
	public CommandHelp() {
		this.setName("help");
		this.setDescription("Lista os comandos disponíveis. Para ajuda mais detalhada, usa " + CommandHandler.COMMAND_SUFFIX + "help <comando>");
		this.setAliases(new String[]{ "?", "ajuda" });
	}

	@Override
	public void handleEvent(Event e) {
		if(getType().equals(e.getType()) && isEqual(e))
			run(e);
	}

	@Override
	public void run(Event e) {
		if("".equals(e.getMessage())) { // No arguments, show the whole command list
			StringBuilder sb = new StringBuilder();
			sb.append("Comandos: ");

			short index = 0;
			for(Command command : CommandHandler.INSTANCE.getListeners()) {
				if(index > 0) sb.append(", ");
				sb.append(command.getName());

				if(command.getAliases().length > 0)
					sb.append(' ').append(Arrays.toString(command.getAliases()));

				index++;
			}
			Bot.INSTANCE.sendMessage(e.getChannel(), sb.toString());
		} else { // Arguments, show the help for the command
			Command command = CommandHandler.INSTANCE.get(e.getMessage());
			if(command != null) { // Command exists, show description
				String description = command.getDescription();
				if("".equals(description))
					Bot.INSTANCE.sendMessage(e.getChannel(), "Não existe descrição disponível para '" + e.getMessage() + "'.");
				else {
					String aliases = command.getAliases().length > 0 ? " " + Arrays.toString(command.getAliases()) : "";
					Bot.INSTANCE.sendMessage(e.getChannel(), command.getName() + aliases + ": " + description);
				}
			} else {
				Bot.INSTANCE.sendMessage(e.getChannel(), "O comando '" + e.getMessage() + "' não existe. para uma lista dos comandos, escreve /help");
			}
		}
	}

	@Override
	public EVENT_TYPE getType() {
		return EVENT_TYPE.COMMAND;
	}
}
