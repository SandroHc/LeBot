package sandrohc.ircbot.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.events.Event;
import sandrohc.ircbot.commands.osu.OsuUser;
import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;
import sandrohc.ircbot.handlers.URLHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommandOsu extends Command {
	private final String apiKey = "ee46e7333c19cf40dfaadf94f0cd63d7f889da0d";

	public CommandOsu() {
		this.setName("osu");
		this.setDescription("Visualizador de estatísticas do osu!");
	}

	@Override
	public void handleEvent(Event e) {
		if(getType().equals(e.getType()) && isEqual(e))
			run(e);
	}

	@Override
	public void run(Event e) {
		String username = "".equals(e.getMessage()) ? e.getSender() : e.getMessage();
		log("Getting user info for " + username);
		String content = URLHandler.getContents(URLHandler.generateURL("https://osu.ppy.sh/api/get_user?k=" + apiKey + "&u=" + username + "&event_days=31"));

		OsuUser[] userData = new Gson().fromJson(content, new TypeToken<OsuUser[]>(){}.getType());
		Bot.INSTANCE.sendMessage(e.getChannel(), userData[0].username + " | rank " + userData[0].pp_rank + " | " + userData[0].pp_raw + " pp | " + round(userData[0].accuracy, 3) + "% accuracy | level " + ((int) userData[0].level) + " | " + userData[0].playcount + " plays");
	}

	private double round(double value, int places) {
		if(places < 0) throw new IllegalArgumentException();

		return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public EVENT_TYPE getType() {
		return EVENT_TYPE.COMMAND;
	}
}
