package ch.bbcag.bubblegum.dao.util;

public class DaoException extends Exception{

	private static final long serialVersionUID = -4349117774394459096L;

	public DaoException(Exception cause) {
		super("Error executing QuarryUnit", cause);
	}
}
