package eu.ubitech.nmapservice.exception;

@SuppressWarnings("serial")
public class NmapOutputParsingException extends Exception {
  public NmapOutputParsingException(Throwable t) {
    super("Errow while parsing NMAP XML-based result.", t);
  }
}
