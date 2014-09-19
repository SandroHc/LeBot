package sandrohc.ircbot.commands.events;

import sandrohc.ircbot.handlers.CommandHandler.EVENT_TYPE;

public class EventAny extends Event {
	@Override
	public EVENT_TYPE getType() {
		return EVENT_TYPE.ANY;
	}
}
