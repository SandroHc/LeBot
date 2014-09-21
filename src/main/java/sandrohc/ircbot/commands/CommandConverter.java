package sandrohc.ircbot.commands;

import java.io.IOException;

public class CommandConverter extends Command {

	public CommandConverter() {
		this.setName("converter");
		this.setDescription("Executa conversões & cálculos..");
		this.setAliases(new String[]{ "calculator", "calc" });
	}

	@Override
	public boolean handleEvent(Event e) throws IOException {
		if(super.handleEvent(e)) {
			// Código aqui
			// e.getMessage() para os argumentos

			return true;
		}
		return false;
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}

	@Override
	public String getSuffix() {
		return "=";
	}
}
