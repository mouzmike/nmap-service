package eu.ubitech.openvas.exceptions;

@SuppressWarnings("serial")
public class OpenvasClientAuthenticationException extends Exception {
	
	public OpenvasClientAuthenticationException(final String message) {
		super(String.format("Unable to authenticate client. Error message is {%s.}", message));
	}
	
}
