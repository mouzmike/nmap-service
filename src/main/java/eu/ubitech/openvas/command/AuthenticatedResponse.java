package eu.ubitech.openvas.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.auth.AuthenticateResponse;

@XmlRootElement(name = "commands_response")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AuthenticatedResponse<T extends Response> extends Response{

  @XmlElement(name = "authenticate_response")
  private AuthenticateResponse authenticateResponse;

  @XmlAnyElement(lax=true) private T commandResponse;

  public AuthenticatedResponse() {}

  public AuthenticatedResponse(AuthenticateResponse authenticateResponse, T commandResponse) {
    super();
    this.authenticateResponse = authenticateResponse;
    this.commandResponse = commandResponse;
  }

  public AuthenticateResponse getAuthenticateResponse() {
    return authenticateResponse;
  }

  public void setAuthenticateResponse(AuthenticateResponse authenticateResponse) {
    this.authenticateResponse = authenticateResponse;
  }

  public T getCommandResponse() {
    return commandResponse;
  }

  public void setCommandResponse(T commandResponse) {
    this.commandResponse = commandResponse;
  }
}
