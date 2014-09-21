package sandrohc.ircbot.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sandrohc.ircbot.Bot;
import sandrohc.ircbot.commands.osu.OsuUser;
import sandrohc.ircbot.utils.URLUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommandOsu extends Command {
	private final String apiKey = "ee46e7333c19cf40dfaadf94f0cd63d7f889da0d";

	@Override
	protected void init() {
		this.setName("osu");
		this.setDescription("Visualizador de estatísticas do osu!");
	}

	@Override
	protected boolean validate(Event e) {
		if(!super.validate(e)) return false;

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

		// If not this command, return false
		return isEqual(commandName);
	}

	@Override
	protected void execute(Event e) {
		String username = e.getMessage().isEmpty() ? e.getSender() : e.getMessage();
		log("Getting user info for " + username);
		String content = URLUtil.getContents(URLUtil.generateURL("https://osu.ppy.sh/api/get_user?k=" + apiKey + "&u=" + username + "&event_days=31"));

		OsuUser[] userData = new Gson().fromJson(content, new TypeToken<OsuUser[]>(){}.getType());
		Bot.INSTANCE.sendMessage(e.getChannel(), userData[0].username + " | rank " + userData[0].pp_rank + " | " + userData[0].pp_raw + " pp | " + round(userData[0].accuracy, 3) + "% accuracy | level " + ((int) userData[0].level) + " | " + userData[0].playcount + " plays");

		log(userData[0].toString());
	}

	private double round(double value, int places) {
		if(places < 0) throw new IllegalArgumentException();

		return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public boolean hasSuffix() {
		return true;
	}
}
