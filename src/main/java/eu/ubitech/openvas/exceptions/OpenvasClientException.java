package eu.ubitech.openvas.exceptions;

@SuppressWarnings("serial")
public class OpenvasClientException extends Exception {
	public OpenvasClientException(Throwable t) {
		super("Generic client error.", t);
	}
}
