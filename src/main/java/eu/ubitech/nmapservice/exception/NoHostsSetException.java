package eu.ubitech.nmapservice.exception;

@SuppressWarnings("serial")
public class NoHostsSetException extends Exception {
  public NoHostsSetException() {
    super("Script requires at least one valid host to execute.");
  }
}
