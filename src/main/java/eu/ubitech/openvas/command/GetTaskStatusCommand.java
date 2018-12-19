package eu.ubitech.openvas.command;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.GetTaskStatusCommand.GetTaskStatusRequest;
import eu.ubitech.openvas.command.GetTaskStatusCommand.GetTaskStatusResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class GetTaskStatusCommand extends Command<GetTaskStatusRequest, GetTaskStatusResponse> {

  @XmlRootElement(name = "get_tasks")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class GetTaskStatusRequest extends Request {

    @XmlAttribute(name = "task_id")
    private String taskId;
  }

  @XmlRootElement(name = "get_tasks_response")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  public static class GetTaskStatusResponse extends Response {

    @XmlElement(name = "task")
    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks() {
      return tasks;
    }

    public void setTasks(List<Task> tasks) {
      this.tasks = tasks;
    }

    @Override
    public String toString() {
      return "GetTaskStatusResponse [tasks=" + tasks + "]";
    }

    @XmlRootElement(name = "task")
    @XmlAccessorType(value = XmlAccessType.FIELD)
    public static class Task {

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getStatus() {
        return status;
      }

      public void setStatus(String status) {
        this.status = status;
      }

      @XmlAttribute(name = "id")
      private String id;

      @XmlElement(name = "name")
      private String name;

      @XmlElement(name = "status")
      private String status;

      @Override
      public String toString() {
        return "Task [id=" + id + ", name=" + name + ", status=" + status + "]";
      }
    }
  }

  @Override
  public GetTaskStatusCommandBuilder builder() {
    return new GetTaskStatusCommandBuilder();
  }

  public static class GetTaskStatusCommandBuilder
      extends AuthenticatedBuilder<GetTaskStatusRequest, GetTaskStatusResponse>
      implements CommandBuilder<GetTaskStatusRequest> {

    private String task;

    @Override
    public GetTaskStatusCommandBuilder build() {

      GetTaskStatusRequest request = new GetTaskStatusRequest();
      request.taskId = this.task;
      this._this =
          new AuthenticatedRequest<GetTaskStatusRequest>(
              new AuthenticateRequest(this.username, this.password), request);

      return this;
    }

    public GetTaskStatusCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this; // TODO Auto-generated method stub
    }

    public GetTaskStatusCommandBuilder forTask(final String task) {
      this.task = task;
      return this; // TODO Auto-generated method stub
    }
  }
}
