package eu.ubitech.openvas.command;

public abstract class Command<Req extends Request, Res extends Response> {

  protected CommandBuilder<Req> builder;
  protected Req _this;
  public abstract CommandBuilder<Req> builder();


}
