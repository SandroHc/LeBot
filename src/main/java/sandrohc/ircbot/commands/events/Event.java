package sandrohc.ircbot.commands.events;

import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;

public abstract class Event {
	protected String channel = "";
	protected String sender = "";
	protected String message = "";

	public abstract EVENT_TYPE getType();

	public String getMessage() {
		return message;
	}

	public Event setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getSender() {
		return sender;
	}

	public Event setSender(String sender) {
		this.sender = sender;
		return this;
	}

	public String getChannel() {
		return channel;
	}

	public Event setChannel(String channel) {
		this.channel = channel;
		return this;
	}

	@Override
	public String toString() {
		return "Event{" + "channel='" + channel + '\'' + ", sender='" + sender + '\'' + ", message='" + message + '\'' + '}';
	}
}
