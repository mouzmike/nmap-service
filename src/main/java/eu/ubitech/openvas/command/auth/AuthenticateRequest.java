package eu.ubitech.openvas.command.auth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authenticate")
@XmlAccessorType(value = XmlAccessType.FIELD)
public final class AuthenticateRequest {
  private Credentials credentials;

  public AuthenticateRequest(final String username, final String password) {
    this.credentials = new Credentials(username, password);
  }

  public AuthenticateRequest() {}

  @XmlRootElement(name = "credentials")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Credentials {

    public Credentials() {}

    public Credentials(final String username, final String password) {
      this.username = username;
      this.password = password;
    }

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  public Credentials getCredentials() {
    return credentials;
  }
}
