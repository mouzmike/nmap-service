package eu.ubitech.openvas.command;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.CreateTargetCommand.CreateTargetRequest;
import eu.ubitech.openvas.command.CreateTargetCommand.CreateTargetResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class CreateTargetCommand extends Command<CreateTargetRequest, CreateTargetResponse> {

  @XmlAccessorType(value = XmlAccessType.FIELD)
  @XmlRootElement(name = "create_target")
  public static class CreateTargetRequest extends Request {

    @XmlElement(name = "hosts")
    private String hosts;

    @XmlElement(name = "name")
    private String name;

    public String getHosts() {
      return hosts;
    }

    public void setHosts(String hosts) {
      this.hosts = hosts;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  @XmlRootElement(name = "create_target_response")
  public static class CreateTargetResponse extends Response {

    public CreateTargetResponse() {}

    @XmlAttribute(name = "id")
    private String resourceId;

    public String getResourceId() {
      return resourceId;
    }

    public void setResourceId(String resourceId) {
      this.resourceId = resourceId;
    }

    @Override
    public String toString() {
      return "CreateTargetResponse [resourceId="
          + resourceId
          + ", status="
          + status
          + ", text="
          + text
          + "]";
    }
  }

  public static class CreateTargetCommandBuilder
      extends AuthenticatedBuilder<CreateTargetRequest, CreateTargetResponse>
      implements CommandBuilder<CreateTargetRequest> {

    private String name;
    private SortedSet<String> hosts = new TreeSet<String>();

    public CreateTargetCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this;
    }

    public CreateTargetCommandBuilder setName(final String name) {
      this.name = name;
      return this;
    }

    public CreateTargetCommandBuilder addHostSpecifier(final String host) {
      this.hosts.add(host);
      return this;
    }

    public CreateTargetCommandBuilder addHostsSpecifier(final Collection<String> hosts) {
      this.hosts.addAll(hosts);
      return this;
    }

  @Override
  public CreateTargetCommandBuilder build() {
    
	CreateTargetRequest request = new   CreateTargetRequest();
	request.setName(this.name);
	request.setHosts(String.join(",", this.hosts));
	this._this = new AuthenticatedRequest<CreateTargetRequest>(new AuthenticateRequest(this.username, this.password),request);
	  
    return this;
  }

 
  }

  @Override
  public CreateTargetCommandBuilder builder() { // TODO Auto-generated method stub
    return new CreateTargetCommandBuilder();
  }
}
