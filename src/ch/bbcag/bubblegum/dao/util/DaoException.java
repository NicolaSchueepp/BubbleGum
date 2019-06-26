package ch.bbcag.bubblegum.dao.util;

public class DaoException extends RuntimeException{

	private static final long serialVersionUID = -4349117774394459096L;

	public DaoException(Exception cause) {
		super("Error executing QuarryUnit:\n" + cause.getMessage(), cause);
	}
}
