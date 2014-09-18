package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.handlers.CommandHandler;

import java.util.Arrays;

public class CommandHelp extends Command {
	public CommandHelp() {
		this.setName("help");
		this.setDescription("Lista os comandos disponíveis. Para ajuda mais detalhada, usa " + CommandHandler.COMMAND_SUFFIX + "help <comando>");
		this.setAliases(new String[]{ "?", "ajuda" });
	}

	@Override
	public void parse(String channel, String sender, String message) {
		if("".equals(message)) { // No arguments, show the whole command list
			StringBuilder sb = new StringBuilder();
			sb.append("Comandos: ");

			short index = 0;
			for(Command command : CommandHandler.INSTANCE.getList()) {
				if(index > 0) sb.append(", ");
				sb.append(command.getName());

				if(command.getAliases().length > 0)
					sb.append(' ').append(Arrays.toString(command.getAliases()));

				index++;
			}
			Bot.INSTANCE.sendMessage(channel, sb.toString());
		} else { // Arguments, show the help for the command
			Command command = CommandHandler.INSTANCE.get(message);
			if(command != null) { // Command exists, show description
				String description = command.getDescription();
				if("".equals(description))
					Bot.INSTANCE.sendMessage(channel, "Não existe descrição disponível para '" + message + "'.");
				else {
					String aliases = command.getAliases().length > 0 ? " " + Arrays.toString(command.getAliases()) : "";
					Bot.INSTANCE.sendMessage(channel, command.getName() + aliases + ": " + description);
				}
			} else {
				Bot.INSTANCE.sendMessage(channel, "O comando '" + message + "' não existe. para uma lista dos comandos, escreve /help");
			}
		}
	}
}
