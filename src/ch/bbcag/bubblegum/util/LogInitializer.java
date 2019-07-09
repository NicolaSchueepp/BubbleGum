package ch.bbcag.bubblegum.util;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogInitializer {

	private final Logger LOGGER;
	
	public LogInitializer(String name) {
		LOGGER = Logger.getLogger(name);
		LOGGER.setUseParentHandlers(false);
		Handler[] handlers = LOGGER.getHandlers();
		for(Handler handler : handlers) {
			LOGGER.removeHandler(handler);
		}
	}
	
	public LogInitializer initConsole() {
		ConsoleHandler handler = new ConsoleHandler();
		SimpleFormatter formatter = new SimpleFormatter();
		handler.setFormatter(formatter);
		LOGGER.addHandler(handler);
		return this;
	}
	
	public LogInitializer initFile() {
		FileHandler handler = null;
		try {
			handler = new FileHandler();
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		SimpleFormatter formatter = new SimpleFormatter();
		handler.setFormatter(formatter);
		LOGGER.addHandler(handler);										
		return this;
	}
	
	public Logger getLogger() {
		return LOGGER;
	}
}
