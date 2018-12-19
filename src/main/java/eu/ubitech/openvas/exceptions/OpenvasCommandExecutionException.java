package eu.ubitech.openvas.exceptions;

@SuppressWarnings("serial")
public class OpenvasCommandExecutionException extends Exception {

  public OpenvasCommandExecutionException(final String message) {
    super(String.format("Unable to execute OMP command (%s).", message));
  }
}
