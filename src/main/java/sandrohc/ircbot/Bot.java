package sandrohc.ircbot;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import sandrohc.ircbot.handlers.CommandHandler;

import java.io.IOException;

public class Bot extends PircBot {
	public static Bot INSTANCE;
	private static String[] channels = { "#cjb" };

	private boolean silentMode = false;

	public Bot() throws IrcException, IOException {
		INSTANCE = this;
		this.setVerbose(true); // Enable debugging output

		this.setName("Victorique-chan");
		this.setAutoNickChange(true);
		this.connect("irc.esper.net"); // Connect to the IRC server

		// Join the channels specified
		for(String channel : channels)
			this.joinChannel(channel);
	}

	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		CommandHandler.INSTANCE.parse(channel, sender, message);

		if(!silentMode && message.equals("( ͡° ͜ʖ ͡°)"))
			sendMessage(channel, "( ͡° ͜ʖ ͡°)");
	}

	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
		if(recipientNick.equals(getNick()))
			joinChannel(channel);
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		if(sender.equals(getNick())) return;

		if(sender.equals("SandroHc"))
			sendMessage(channel, "Ohayo goshujin-sama!");
	}

	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		CommandHandler.INSTANCE.parse(sender, sender, message);

		if(sender.equals("SandroHc")) {
			if(message.equals("stop")) {
				silentMode = !silentMode;
				sendMessage("SandroHc", silentMode ? "Ok ok, goshujin-sama. :(" : "As you wish, ore no goshujin-sama!");
			} else if(message.equals("quit")) {
				sendMessage("SandroHc", "Sayounara goshujin-sama.");
				this.disconnect();
			}
		}
	}

	@Override
	protected void onDisconnect() {
		System.exit(0);
	}

	@Override
	protected void onConnect() {
		this.identify("biscoitos123");
	}
}
