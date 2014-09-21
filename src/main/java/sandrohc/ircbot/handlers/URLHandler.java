package sandrohc.ircbot.handlers;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLHandler {

	/**
	 * Create and handle any exceptions that may be thrown by URL.
	 * @param url The URI
	 * @return The URL
	 */
	public static URL generateURL(String url) {
		try {
			return new URL(url);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the HTML body of the URL.
	 * @param url The URL to get the contents
	 * @return the HTML body of the URL.
	 */
	public static String getContents(URL url) {
		if(url == null) throw new NullPointerException("url can not be null!");

		try {
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;

			return IOUtils.toString(in, encoding);
		} catch(IOException e) {
			e.printStackTrace();
		}

		return "";
	}
}
