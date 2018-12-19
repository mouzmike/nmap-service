package eu.ubitech.openvas.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.auth.AuthenticateRequest;

@XmlRootElement(name="commands")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AuthenticatedRequest<T extends Request> {

  @XmlElement(name="authenticate") private AuthenticateRequest authenticateRequest;
  @XmlAnyElement
  private T commandRequest;

  public AuthenticatedRequest() {}

  public AuthenticatedRequest(AuthenticateRequest authenticateRequest, T commandRequest) {
    super();
    this.authenticateRequest = authenticateRequest;
    this.commandRequest = commandRequest;
  }

  public AuthenticateRequest getAuthenticateRequest() {
    return authenticateRequest;
  }

  public void setAuthenticateRequest(AuthenticateRequest authenticateRequest) {
    this.authenticateRequest = authenticateRequest;
  }

  public T getCommandRequest() {
    return commandRequest;
  }

  public void setCommandRequest(T commandRequest) {
    this.commandRequest = commandRequest;
  }
}
