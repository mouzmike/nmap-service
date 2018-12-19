package eu.ubitech.openvas.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.GetResultsCommand.GetResultsRequest;
import eu.ubitech.openvas.command.GetResultsCommand.GetResultsResponse;
import eu.ubitech.openvas.command.auth.AuthenticateRequest;

public class GetResultsCommand extends Command<GetResultsRequest, GetResultsResponse> {

  @XmlRootElement(name = "get_results")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class GetResultsRequest extends Request {

    @XmlAttribute(name = "task_id")
    private String taskId;
  }

  @XmlRootElement(name = "get_results_response")
  @XmlAccessorType(value = XmlAccessType.FIELD)
  public static class GetResultsResponse extends Response {

    @XmlElement(name = "result")
    private List<Result> results = new ArrayList<Result>();

    @Override
    public String toString() {
      return "GetResultsResponse [results="
          + results
          + ", status="
          + status
          + ", text="
          + text
          + "]";
    }

    @XmlRootElement(name = "result")
    @XmlAccessorType(value = XmlAccessType.FIELD)
    public static class Result {

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

      public String getPort() {
        return port;
      }

      public void setPort(String port) {
        this.port = port;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public String getThreat() {
        return threat;
      }

      public void setThreat(String threat) {
        this.threat = threat;
      }

      public BigDecimal getSeverity() {
        return severity;
      }

      public void setSeverity(BigDecimal severity) {
        this.severity = severity;
      }

      public ResultHost getHost() {
        return host;
      }

      public void setHost(ResultHost host) {
        this.host = host;
      }

      public NVT getNvt() {
        return nvt;
      }

      public void setNvt(NVT nvt) {
        this.nvt = nvt;
      }

      @XmlAttribute(name = "id")
      private String id;

      @XmlElement(name = "name")
      private String name;

      @XmlElement(name = "port")
      private String port;

      @XmlElement(name = "description")
      private String description;

      @XmlElement(name = "threat")
      private String threat;

      @XmlElement(name = "severity")
      private BigDecimal severity;

      @XmlElement(name = "host")
      private ResultHost host;

      @XmlElement(name = "nvt")
      private NVT nvt;

      @XmlAccessorType(value = XmlAccessType.FIELD)
      public static class NVT {

        @XmlAttribute(name = "oid")
        private String oid;

        public String getOid() {
          return oid;
        }

        public void setOid(String oid) {
          this.oid = oid;
        }

        public String getName() {
          return name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String getType() {
          return type;
        }

        public void setType(String type) {
          this.type = type;
        }

        public String getFamily() {
          return family;
        }

        public void setFamily(String family) {
          this.family = family;
        }

        public String getCve() {
          return cve;
        }

        public void setCve(String cve) {
          this.cve = cve;
        }

        public String getBid() {
          return bid;
        }

        public void setBid(String bid) {
          this.bid = bid;
        }

        public String getXref() {
          return xref;
        }

        public void setXref(String xref) {
          this.xref = xref;
        }

        public String getTags() {
          return tags;
        }

        public void setTags(String tags) {
          this.tags = tags;
        }

        public String getCvss() {
          return cvss;
        }

        public void setCvss(String cvss) {
          this.cvss = cvss;
        }

        @XmlElement(name = "name")
        private String name;

        @XmlElement(name = "type")
        private String type;

        @XmlElement(name = "family")
        private String family;

        @XmlElement(name = "cve")
        private String cve;

        @XmlElement(name = "bid")
        private String bid;

        @XmlElement(name = "xref")
        private String xref;

        @XmlElement(name = "tags")
        private String tags;

        @XmlElement(name = "cvss_base")
        private String cvss;

        @Override
        public String toString() {
          return "NVT [oid="
              + oid
              + ", name="
              + name
              + ", type="
              + type
              + ", family="
              + family
              + ", cve="
              + cve
              + ", bid="
              + bid
              + ", xref="
              + xref
              + ", tags="
              + tags
              + ", cvss="
              + cvss
              + "]";
        }
      }

      @XmlAccessorType(value = XmlAccessType.FIELD)
      public static class ResultHost {

        @XmlAnyElement @XmlMixed private List<Object> host;

        @Override
        public String toString() {
          return "ResultHost [host="
              + String.join(
                  ",",
                  host.stream()
                      .filter(t -> t instanceof String)
                      .map(t -> t.toString())
                      .collect(Collectors.toList()))
              + "]";
        }

        public List<Object> getHost() {
          return host;
        }

        public void setHost(List<Object> host) {
          this.host = host;
        }
      }

      @Override
      public String toString() {
        return "Result [id="
            + id
            + ", name="
            + name
            + ", port="
            + port
            + ", description="
            + description
            + ", threat="
            + threat
            + ", severity="
            + severity
            + ", host="
            + host
            + ", nvt="
            + nvt
            + "]";
      }
    }

    public List<Result> getResults() {
      return results;
    }

    public void setResults(List<Result> results) {
      this.results = results;
    }
  }

  @Override
  public GetResultsCommandBuilder builder() {
    return new GetResultsCommandBuilder();
  }

  public static class GetResultsCommandBuilder
      extends AuthenticatedBuilder<GetResultsRequest, GetResultsResponse>
      implements CommandBuilder<GetResultsRequest> {

    private String task;

    @Override
    public GetResultsCommandBuilder build() {

      GetResultsRequest request = new GetResultsRequest();
      request.taskId = this.task;
      this._this =
          new AuthenticatedRequest<GetResultsRequest>(
              new AuthenticateRequest(this.username, this.password), request);

      return this;
    }

    public GetResultsCommandBuilder withAuthentication(
        final String username, final String password) {
      this.username = username;
      this.password = password;
      return this; // TODO Auto-generated method stub
    }

    public GetResultsCommandBuilder forTask(final String task) {
      this.task = task;
      return this; // TODO Auto-generated method stub
    }
  }
}
