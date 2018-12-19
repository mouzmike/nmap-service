package eu.ubitech.openvas.command;

public abstract class AuthenticatedBuilder<Req,Res extends Response> extends CommandSender<Req,Res> {
  protected String username;
  protected String password;
}
