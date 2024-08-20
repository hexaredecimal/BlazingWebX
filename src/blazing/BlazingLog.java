/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blazing;

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;

/**
 *
 * @author hexaredecimal
 */

public class BlazingLog {
	private static Logger logger ;
	
	static {
		final String RESET = "\u001B[0m";
		final String RED = "\u001B[31m";
		final String GREEN = "\u001B[32m";
		final String YELLOW = "\u001B[33m";
		final String BLUE = "\u001B[34m";
		final String CYAN = "\u001B[36m";
		final String BLACK = "\u001B[30m";
		logger = Logger.getLogger(Blazing.class.getName());
		for (var handler : logger.getHandlers()) {
			logger.removeHandler(handler);
		}
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		handler.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record) {
				String color;

				// Determine the color based on log level
				String lvl_name = record.getLevel().getName();
				if ("SEVERE".equals(lvl_name))
						color = RED;
				else  if ("WARNING".equals(lvl_name))
						color = YELLOW;
				else if ("INFO".equals(lvl_name))
						color = GREEN;
				else if ("CONFIG".equals(lvl_name))
						color = CYAN;
				else
						color = BLUE;
				

				// Format the message with the desired format and color
				int _hour= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				int _minute = Calendar.getInstance().get(Calendar.MINUTE);
				int _second = Calendar.getInstance().get(Calendar.SECOND);
				String hour = _hour < 10 ? String.format("0%d", _hour) : String.valueOf(_hour);
				String minute = _minute < 10 ? String.format("0%d", _minute) : String.valueOf(_minute);
				String second = _second < 10 ? String.format("0%d", _second) : String.valueOf(_second);
				String time = String.format("%s:%s:%s", hour, minute, second);
				long millis = record.getMillis() % 1000;  // Milliseconds
				return String.format(
					"%s %s[%s]%s %dms\t%s%s%n",
					time, 
					color, // Color for level
					record.getLevel().getLocalizedName().toLowerCase(), // Level in lower case
					RESET + BLUE, // Reset color for following text
					millis, // Milliseconds
					RESET + BLACK, // Black color for message
					record.getMessage() // Log message
				);
			}
		}
		);
		logger.addHandler(handler);
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
	}

	public static void warning(String message) {
		logger.warning(message);
	}

	public static void info(String message) {
		logger.info(message);
	}
	public static void severe(String message) {
		logger.severe(message);
	}

	public static void fine(String message) {
		logger.fine(message);
	}
}
