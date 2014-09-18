package sandrohc.ircbot.handlers;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHandler {
	private static Logger log;

	static {
		log = Logger.getLogger(LogHandler.class.getName());
		log.setUseParentHandlers(false);
		log.setLevel(Level.ALL);

		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		log.addHandler(handler);

//		try {
//			FileHandler fileHandler = new FileHandler(System.getProperty("user.home") + ".ggo/log.log");
//			fileHandler.setLevel(Level.ALL);
//			log.addHandler(fileHandler);
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void log(Level level, String msg) {
		log.log(level, msg);
	}

	/**
	 * Log a SEVERE message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void severe(Object msg) {
		log(Level.SEVERE, String.valueOf(msg));
	}

	/**
	 * Log a WARNING message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void warning(Object msg) {
		log(Level.WARNING, String.valueOf(msg));
	}

	/**
	 * Log an INFO message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void info(Object msg) {
		log(Level.INFO, String.valueOf(msg));
	}

	/**
	 * Log a CONFIG message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void config(Object msg) {
		log(Level.CONFIG, String.valueOf(msg));
	}

	/**
	 * Log a FINE message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void fine(Object msg) {
		log(Level.FINE, String.valueOf(msg));
	}

	/**
	 * Log a FINER message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void finer(Object msg) {
		log(Level.FINER, String.valueOf(msg));
	}

	/**
	 * Log a FINEST message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void finest(Object msg) {
		log(Level.FINEST, String.valueOf(msg));
	}

	/**
	 * Log a DEBUG message.
	 * @param   msg     The string message (or a key in the message catalog)
	 */
	public static void debug(Object msg) {
		if(true) log(Level.INFO, String.valueOf(msg));
	}
}
