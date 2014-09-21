package sandrohc.ircbot.commands;

import com.google.gson.Gson;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.google.GoogleResults;
import sandrohc.ircbot.handlers.URLHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CommandGoogle extends Command {

	public CommandGoogle() {
		this.setName("google");
		this.setDescription("Executa uma pesquisa Google rápida.");
	}

	@Override
	public boolean handleEvent(Event e) throws IOException {
		if(super.handleEvent(e)) {
			String content = URLHandler.getContents(URLHandler.generateURL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + URLEncoder.encode(e.getMessage(), "UTF-8")));
			GoogleResults results = new Gson().fromJson(content, GoogleResults.class);

			// Show title and URL of 1st result.
			Bot.INSTANCE.sendMessage(e.getChannel(), URLDecoder.decode(results.getResponseData().getResults().get(0).getTitle() + " (\u0002" + results.getResponseData().getResults().get(0).getUrl() + "\u000F)", "UTF-8"));

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
		return "?";
	}
}
