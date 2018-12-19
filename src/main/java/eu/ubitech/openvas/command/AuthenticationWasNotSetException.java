package eu.ubitech.openvas.command;

public class AuthenticationWasNotSetException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public AuthenticationWasNotSetException() {
		super("Authentication credentials [username, password] have not been provided!");
	}
}
