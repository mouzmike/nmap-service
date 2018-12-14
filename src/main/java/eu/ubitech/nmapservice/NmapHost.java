package eu.ubitech.nmapservice;

import java.util.ArrayList;
import java.util.List;

public class NmapHost {
  private String ipV4;
  private List<String> vulnerabilites = new ArrayList<>();

  public String getIpV4() {
    return ipV4;
  }

  public void setIpV4(String ipV4) {
    this.ipV4 = ipV4;
  }

  public List<String> getVulnerabilites() {
    return vulnerabilites;
  }

  public void setVulnerabilites(List<String> vulnerabilites) {
    this.vulnerabilites = vulnerabilites;
  }
}
