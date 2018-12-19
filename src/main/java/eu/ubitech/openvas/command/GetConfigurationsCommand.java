package eu.ubitech.openvas.command;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.GetConfigurationsCommand.GetConfigurationsRequest;
import eu.ubitech.openvas.command.GetConfigurationsCommand.GetConfigurationsResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class GetConfigurationsCommand
    extends Command<GetConfigurationsRequest, GetConfigurationsResponse> {

  @XmlRootElement(name = "get_configs")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class GetConfigurationsRequest extends Request {}

  @XmlRootElement(name = "get_configs_response")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  public static class GetConfigurationsResponse extends Response {

    @Override
    public String toString() {
      return "GetConfigurationsResponse [configurations="
          + configurations
          + ", status="
          + status
          + ", text="
          + text
          + "]";
    }

    @XmlElement(name = "config")
    private List<Configuration> configurations;

    @XmlRootElement(name = "config")
    @XmlAccessorType(value = XmlAccessType.FIELD)
    public static class Configuration {
      @XmlAttribute(name = "id")
      private String configurationId;

      @Override
      public String toString() {
        return "Configuration [configurationId="
            + configurationId
            + ", name="
            + name
            + ", comment="
            + comment
            + "]";
      }

      @XmlElement(name = "name")
      private String name;

      @XmlElement(name = "comment")
      private String comment;

      public String getConfigurationId() {
        return configurationId;
      }

      public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getComment() {
        return comment;
      }

      public void setComment(String comment) {
        this.comment = comment;
      }
    }

    public List<Configuration> getConfigurations() {
      return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
      this.configurations = configurations;
    }
  }

  @Override
  public GetConfigurationsCommandBuilder builder() { // TODO Auto-generated method stub
    return new GetConfigurationsCommandBuilder();
  }

  public static class GetConfigurationsCommandBuilder
      extends AuthenticatedBuilder<GetConfigurationsRequest, GetConfigurationsResponse>
      implements CommandBuilder<GetConfigurationsRequest> {

    private String username;
    private String password;

    public GetConfigurationsCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this;
    }

    @Override
    public GetConfigurationsCommandBuilder build() {
      GetConfigurationsRequest request = new GetConfigurationsRequest();
      this._this =
          new AuthenticatedRequest<GetConfigurationsRequest>(
              new AuthenticateRequest(this.username, this.password), request);
      return this;
    }
  }
}
