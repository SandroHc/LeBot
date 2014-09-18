package sandrohc.ircbot.handlers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
	private boolean verbose = false;
	private String formatter = "%s %s: %s\n"; // date type: message
	private String formatterVerbose = "[%s] %s $s: %s\n%s"; // [source] date type: message\n throwable

	// format string for printing the log record
	private final Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss"); // dd/MM/yyyy

	public synchronized String format(LogRecord record) {
		String source;
		if(record.getSourceClassName() != null) {
			source = record.getSourceClassName();
			if(record.getSourceMethodName() != null) source += "." + record.getSourceMethodName() + "()";
		} else source = record.getLoggerName();
		String message = formatMessage(record);
		String throwable = "";
		if(record.getThrown() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			record.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
		}

		date.setTime(record.getMillis());

		if(verbose)
			return String.format(formatterVerbose, dateFormat.format(date), record.getLevel().getName(), source, message, throwable);
		else
			return String.format(formatter, dateFormat.format(date), record.getLevel().getName(), message);
	}
}