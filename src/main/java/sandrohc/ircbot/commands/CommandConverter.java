package sandrohc.ircbot.commands;

public class CommandConverter extends Command {

	@Override
	protected void init() {
		this.setName("converter");
		this.setDescription("Executa conversões & cálculos..");
		this.setAliases(new String[]{ "calculator", "calc" });
	}

	@Override
	protected void execute(Event e)  {
		// Código aqui
		// e.getMessage() para os argumentos
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
