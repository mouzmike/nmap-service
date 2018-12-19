package eu.ubitech.openvas.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.FIELD)
public abstract class ResourceResponse extends Response {

  @XmlAttribute(name = "id")
  protected String resourceId;

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }
}
