package sandrohc.ircbot;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import sandrohc.ircbot.handlers.CommandHandler;
import sandrohc.ircbot.handlers.LogHandler;

import java.io.IOException;

public class Bot extends PircBot {
	public static Bot INSTANCE;
	private static String[] channels = { "#geral" };

	private boolean stopProcessingMessages = false;

	public Bot() throws IrcException, IOException {
		INSTANCE = this;
		this.setVerbose(false); // Enable debugging output

		LogHandler.info("Iniciando bot...");

		this.setName("victorique-chan");
		this.connect("estudantesdeprimidos.irc.slack.com", 6667, "estudantesdeprimidos.s7GCS8YdZRvMVYWRQCrv"); // Connect to the IRC server

		// Join the channels specified
		for(String channel : channels)
			this.joinChannel(channel);
	}

	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		LogHandler.info("MSG on " + channel + " by [" + sender + "!" + login + "@" + hostname + "] " + message);

		if(!stopProcessingMessages)
			CommandHandler.INSTANCE.parse(channel, sender, message);
	}

	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
		if(recipientNick.equals(getNick()))
			joinChannel(channel);
	}

	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		if(sender.equalsIgnoreCase("SandroHc"))
			sendMessage(channel, "Ohayo goshujin-sama!");
	}

	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		LogHandler.info("PRVMSG [" + sender + "!" + login + "@" + hostname + "] " + message);

		if(!stopProcessingMessages)
			CommandHandler.INSTANCE.parse(sender, sender, message);

		if(sender.equalsIgnoreCase("SandroHc")) {
			if(message.startsWith("\"")) {
				sendMessage("#geral", message.substring(1));
			} else if(message.equals("stop")) {
				stopProcessingMessages = !stopProcessingMessages;
				sendMessage("SandroHc", stopProcessingMessages ? "Ok ok, goshujin-sama. :(" : "As you wish, watashi no goshujin-sama!");
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
		LogHandler.info("Conectado ao servidor.");
//		this.identify("biscoitos123");
	}
}
