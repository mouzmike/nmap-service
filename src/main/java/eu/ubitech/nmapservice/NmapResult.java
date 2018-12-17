package eu.ubitech.nmapservice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "nmaprun")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class NmapResult {

  @XmlAttribute(name = "args")
  private String args;

  @XmlElement(name = "host")
  private List<Host> hosts;

  @Override
  public String toString() {
    return "NmapResult [args=" + args + ", hosts=" + hosts + "]";
  }

  public List<Host> getHosts() {
    return hosts;
  }

  public void setHosts(List<Host> hosts) {
    this.hosts = hosts;
  }

  public String getArgs() {
    return args;
  }

  public void setArgs(String args) {
    this.args = args;
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Host {

    public HostPorts getHostports() {
      return hostports;
    }

    public void setHostports(HostPorts hostports) {
      this.hostports = hostports;
    }

    @XmlElement(name = "hostnames")
    private Hostnames hostnames;

    @XmlElement(name = "ports")
    private HostPorts hostports;

    public Hostnames getHostnames() {
      return hostnames;
    }

    public void setHostnames(Hostnames hostnames) {
      this.hostnames = hostnames;
    }

    @Override
    public String toString() {
      return "Host [hostnames=" + hostnames + ", hostports=" + hostports + "]";
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Hostnames {

    @XmlElement(name = "hostname")
    private List<Hostname> hostnames;

    public List<Hostname> getHostnames() {
      return hostnames;
    }

    @Override
    public String toString() {
      return "Hostnames [hostnames=" + hostnames + "]";
    }

    public void setHostnames(List<Hostname> hostnames) {
      this.hostnames = hostnames;
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Hostname {
    @XmlAttribute private String name;
    @XmlAttribute private String type;

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

    @Override
    public String toString() {
      return "Hostname [name=" + name + ", type=" + type + "]";
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class HostPorts {

    @XmlElement(name = "port")
    private List<HostPort> hostPortList;

    @Override
    public String toString() {
      return "HostPorts [hostPortList=" + hostPortList + "]";
    }

    public List<HostPort> getHostPortList() {
      return hostPortList;
    }

    public void setHostPortList(List<HostPort> hostPortList) {
      this.hostPortList = hostPortList;
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class HostPort {
    @XmlAttribute(name = "protocol")
    private String protocol;

    @XmlAttribute(name = "portid")
    private int port;

    @XmlElement(name = "script")
    private List<HostPortScript> scripts;

    @Override
    public String toString() {
      return "HostPort [protocol=" + protocol + ", port=" + port + ", scripts=" + scripts + "]";
    }

    public String getProtocol() {
      return protocol;
    }

    public void setProtocol(String protocol) {
      this.protocol = protocol;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public List<HostPortScript> getScripts() {
      return scripts;
    }

    public void setScripts(List<HostPortScript> scripts) {
      this.scripts = scripts;
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class HostPortScript {
    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "output")
    private String output;

    @XmlElement(name = "elem")
    private List<Element> elements;

    @XmlElement(name = "table")
    private List<Table> tables;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getOutput() {
      return output;
    }

    public void setOutput(String output) {
      this.output = output;
    }

    public List<Element> getElements() {
      return elements;
    }

    public void setElements(List<Element> elements) {
      this.elements = elements;
    }

    public List<Table> getTables() {
      return tables;
    }

    public void setTables(List<Table> tables) {
      this.tables = tables;
    }

    @Override
    public String toString() {
      return "HostPortScript [id="
          + id
          + ", output="
          + output
          + ", elements="
          + elements
          + ", tables="
          + tables
          + "]";
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Table {

    @XmlAttribute(name = "key")
    private String key;

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public List<Element> getElements() {
      return elements;
    }

    public void setElements(List<Element> elements) {
      this.elements = elements;
    }

    public List<Table> getTables() {
      return tables;
    }

    public void setTables(List<Table> tables) {
      this.tables = tables;
    }

    @XmlElement(name = "elem")
    private List<Element> elements;

    @XmlElement(name = "table")
    private List<Table> tables;

    @Override
    public String toString() {
      return "Table [key=" + key + ", elements=" + elements + ", tables=" + tables + "]";
    }
  }

  @XmlAccessorType(value = XmlAccessType.FIELD)
  static class Element {
    @XmlAttribute(name = "key")
    private String key;

    @XmlValue private String text;

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return "Element [key=" + key + ", text=" + text + "]";
    }
  }
}
