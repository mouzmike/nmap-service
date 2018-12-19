package eu.ubitech.openvas.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.CreateTaskCommand.CreateTaskRequest;
import eu.ubitech.openvas.command.CreateTaskCommand.CreateTaskRequest.Target;
import eu.ubitech.openvas.command.CreateTaskCommand.CreateTaskRequest.TaskConfiguration;
import eu.ubitech.openvas.command.CreateTaskCommand.CreateTaskResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class CreateTaskCommand extends Command<CreateTaskRequest, CreateTaskResponse> {

  @XmlAccessorType(value = XmlAccessType.FIELD)
  @XmlRootElement(name = "create_task")
  static class CreateTaskRequest extends Request {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "comment")
    private String comment;

    @XmlElement(name = "target")
    private Target target;

    @XmlElement(name = "config")
    private TaskConfiguration configuration;

    @XmlAccessorType(value = XmlAccessType.FIELD)
    @XmlRootElement(name = "config")
    static class TaskConfiguration {
      @XmlAttribute(name = "id")
      private String id;

      public TaskConfiguration(final String id) {
        this.id = id;
      }

      public TaskConfiguration() {}
    }

    @XmlAccessorType(value = XmlAccessType.FIELD)
    @XmlRootElement(name = "target")
    static class Target {
      @XmlAttribute(name = "id")
      private String id;

      public Target(final String id) {
        this.id = id;
      }

      public Target() {}
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  @XmlRootElement(name = "create_task_response")
  public static class CreateTaskResponse extends ResourceResponse {}

  @Override
  public CreateTaskCommandBuilder builder() { // TODO Auto-generated method stub
    return new CreateTaskCommandBuilder();
  }

  public static class CreateTaskCommandBuilder
      extends AuthenticatedBuilder<CreateTaskRequest, CreateTaskResponse>
      implements CommandBuilder<CreateTaskRequest> {

    private String username;
    private String password;
    private String configuration;
    private String target;
    private String name;
    private String comment;

    public CreateTaskCommandBuilder setName(final String name) {
      this.name = name;
      return this;
    }

    public CreateTaskCommandBuilder setComment(final String comment) {
      this.comment = comment;
      return this;
    }

    public CreateTaskCommandBuilder setTarget(final String target) {
      this.target = target;
      return this;
    }

    public CreateTaskCommandBuilder setConfiguration(final String configuration) {
      this.configuration = configuration;
      return this;
    }

    public CreateTaskCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this;
    }

    @Override
    public CreateTaskCommandBuilder build() {
      CreateTaskRequest request = new CreateTaskRequest();
      request.configuration = new TaskConfiguration(this.configuration);
      request.target = new Target(this.target);
      request.name = this.name;
      request.comment = this.comment;
      this._this =
          new AuthenticatedRequest<CreateTaskRequest>(
              new AuthenticateRequest(this.username, this.password), request);
      return this;
    }
  }
}
