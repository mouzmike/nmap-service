package eu.ubitech.openvas.command;

public abstract class AuthenticatedBuilder<Req, Res extends Response>
    extends CommandSender<Req, Res> {
  protected String username;
  protected String password;

  public AuthenticatedBuilder<Req, Res> withAuthentication(
      final String username, final String password) {
    this.username = username;
    this.password = password;
    return this;
  }
}
