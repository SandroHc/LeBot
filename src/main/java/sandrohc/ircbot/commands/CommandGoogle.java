package sandrohc.ircbot.commands;

import com.google.gson.Gson;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.google.GoogleResults;
import sandrohc.ircbot.utils.TextEffectUtil;
import sandrohc.ircbot.utils.TextEffectUtil.COLOR;
import sandrohc.ircbot.utils.TextEffectUtil.EFFECT;
import sandrohc.ircbot.utils.URLUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CommandGoogle extends Command {

	@Override
	protected void init() {
		this.setName("google");
		this.setDescription("Executa uma pesquisa Google rápida.");
	}

	@Override
	protected void execute(Event e) {
		try {
			String content = URLUtil.getContents(URLUtil.generateURL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + URLEncoder.encode(e.getMessage(), "UTF-8")));
			GoogleResults results = new Gson().fromJson(content, GoogleResults.class);

			// Show title and URL of 1st result.
			Bot.INSTANCE.sendMessage(e.getChannel(), URLDecoder.decode(results.getResponseData().getResults().get(0).getTitle().replaceAll("<b>([^<]*)</b>", EFFECT.BOLD.getChar() + "$1" + EFFECT.BOLD.getChar()) + ' ' + TextEffectUtil.applyColor(results.getResponseData().getResults().get(0).getUrl(), COLOR.BLUE, COLOR.WHITE), "UTF-8"));
		} catch(UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}

	@Override
	public String getSuffix() {
		return "?";
	}

	@Override
	public String getUse() {
		return "?<pesquisa>";
	}

	@Override
	public String getExampleUse() {
		return "?google";
	}

	@Override
	public boolean onlyOps() {
		return false;
	}
}
