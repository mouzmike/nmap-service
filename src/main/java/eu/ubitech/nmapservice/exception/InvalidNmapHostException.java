package eu.ubitech.nmapservice.exception;

@SuppressWarnings("serial")
public class InvalidNmapHostException extends Exception {
  public InvalidNmapHostException(final String host) {
    super(String.format("Host %s is not a valid IPv4", host));
  }
}
