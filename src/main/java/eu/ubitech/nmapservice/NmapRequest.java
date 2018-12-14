package eu.ubitech.nmapservice;

import java.util.List;

public class NmapRequest {
  private List<String> hosts;
  private String callbackURL;
  private String authorization;

  public List<String> getHosts() {
    return hosts;
  }

  public void setHosts(List<String> hosts) {
    this.hosts = hosts;
  }

  public String getCallbackURL() {
    return callbackURL;
  }

  public void setCallbackURL(String callbackURL) {
    this.callbackURL = callbackURL;
  }

  public String getAuthorization() {
    return authorization;
  }

  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }
}
