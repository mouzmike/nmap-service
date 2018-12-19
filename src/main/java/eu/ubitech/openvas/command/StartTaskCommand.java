package eu.ubitech.openvas.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.StartTaskCommand.StartTaskRequest;
import eu.ubitech.openvas.command.StartTaskCommand.StartTaskResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class StartTaskCommand extends Command<StartTaskRequest, StartTaskResponse> {

  @XmlRootElement(name = "start_task")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class StartTaskRequest extends Request {

    @XmlAttribute(name = "task_id")
    private String task;
  }

  @XmlRootElement(name = "start_task_response")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  public static class StartTaskResponse extends Response {}

  @Override
  public StartTaskCommandBuilder builder() {
    // TODO Auto-generated method stub
    return new StartTaskCommandBuilder();
  }

  public static class StartTaskCommandBuilder
      extends AuthenticatedBuilder<StartTaskRequest, StartTaskResponse>
      implements CommandBuilder<StartTaskRequest> {

    private String username;
    private String password;
    private String task;

    public StartTaskCommandBuilder setTask(final String task) {
      this.task = task;
      return this;
    }

    public StartTaskCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this;
    }

    @Override
    public StartTaskCommandBuilder build() {
      StartTaskRequest request = new StartTaskRequest();
      request.task = task;
      this._this =
          new AuthenticatedRequest<StartTaskRequest>(
              new AuthenticateRequest(this.username, this.password), request);
      return this;
    }
  }
}
