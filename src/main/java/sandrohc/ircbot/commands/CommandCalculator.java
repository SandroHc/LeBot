package sandrohc.ircbot.commands;

import sandrohc.ircbot.Bot;
import sandrohc.ircbot.utils.TextEffectUtil;
import sandrohc.ircbot.utils.TextEffectUtil.EFFECT;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CommandCalculator extends Command {

	@Override
	protected void init() {
		this.setName("calculator");
		this.setDescription("Executa cálculos matemáticos.");
		this.setAliases(new String[]{ "calc" });
	}

	@Override
	protected void execute(Event e)  {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
			Bot.INSTANCE.sendMessage(e.getChannel(), TextEffectUtil.applyEffect(String.valueOf(engine.eval(e.getMessage())), EFFECT.BOLD));
		} catch(ScriptException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}

	@Override
	public String getSuffix() {
		return "=";
	}

	@Override
	public String getUse() {
		return "=<operação>";
	}

	@Override
	public String getExampleUse() {
		return "=1+1";
	}

	@Override
	public boolean onlyOps() {
		return false;
	}
}
