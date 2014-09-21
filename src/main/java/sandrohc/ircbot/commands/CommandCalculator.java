package sandrohc.ircbot.commands;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.utils.TextEffectUtil;
import sandrohc.ircbot.utils.TextEffectUtil.COLOR;
import sandrohc.ircbot.utils.TextEffectUtil.EFFECT;

public class CommandCalculator extends Command {
	private DoubleEvaluator evaluator;

	@Override
	protected void init() {
		this.setName("calculator");
		this.setDescription("Executa cálculos matemáticos.");
		this.setAliases(new String[]{ "calc" });

		this.evaluator = new DoubleEvaluator();
	}

	@Override
	protected void execute(Event e)  {
		try {
			Double result = evaluator.evaluate(e.getMessage());

//			ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
			Bot.INSTANCE.sendMessage(e.getChannel(), TextEffectUtil.applyEffect(String.valueOf(result), EFFECT.BOLD));
		} catch(IllegalArgumentException e1) {
			Bot.INSTANCE.sendMessage(e.getChannel(), TextEffectUtil.applyColor(e1.getMessage(), COLOR.LIGHT_RED, COLOR.WHITE));
			log(e1.getMessage());
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
