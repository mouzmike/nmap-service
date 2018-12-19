package eu.ubitech.openvas;

import java.util.stream.Collectors;

import eu.ubitech.openvas.exceptions.OpenvasClientAuthenticationException;
import eu.ubitech.openvas.exceptions.OpenvasClientException;
import eu.ubitech.openvas.exceptions.OpenvasCommandExecutionException;

public class Test {

  public static void main(String[] args)
      throws OpenvasClientAuthenticationException, OpenvasCommandExecutionException,
          OpenvasClientException {

    String rand = "test-final";

    String newtarget =
        OpenvasClient.CREATE_TARGET
            .builder()
            .withAuthentication("admin", "admin")
            .setName(rand)
            .addHostSpecifier("192.168.2.1")
            .addHostSpecifier("192.168.2.10")
            .build()
            .send("218ffb30ff7a", 19390)
            .getResourceId();

    System.out.println("created new target " + newtarget);

    String configuration =
        OpenvasClient.LIST_CONFIGURATIONS
            .builder()
            .withAuthentication("admin", "admin")
            .build()
            .send("218ffb30ff7a", 19390)
            .getConfigurations()
            .stream()
            .filter(f -> f.getName().startsWith("Full and fast"))
            .collect(Collectors.toList())
            .get(0)
            .getConfigurationId();

    System.out.println("selected config " + configuration);

    String task =
        OpenvasClient.CREATE_TASK
            .builder()
            .withAuthentication("admin", "admin")
            .setName(rand)
            .setConfiguration(configuration)
            .setTarget(newtarget)
            .build()
            .send("218ffb30ff7a", 19390)
            .getResourceId();

    System.out.println("created task " + task);

    System.out.println(
        OpenvasClient.RUN_TASK
            .builder()
            .withAuthentication("admin", "admin")
            .setTask(task)
            .build()
            .send("218ffb30ff7a", 19390));
  }
}
