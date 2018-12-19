package eu.ubitech.openvas;

import java.util.stream.Collectors;

import eu.ubitech.openvas.exceptions.OpenvasClientAuthenticationException;
import eu.ubitech.openvas.exceptions.OpenvasClientException;
import eu.ubitech.openvas.exceptions.OpenvasCommandExecutionException;

public class OpenvasClientTest {
	 public static void main(String[] args)
		      throws OpenvasClientAuthenticationException, OpenvasCommandExecutionException,
		          OpenvasClientException {

		    String rand = "20181219";
		    String user = "admin", pass = "admin";
		    String openvasHost = "218ffb30ff7a";
		    int openvasHostPort = 19390;

		    String newtarget =
		        OpenvasClient.CREATE_TARGET
		            .builder()
		            .withAuthentication(user, pass)
		            .setName(rand)
		            .addHostSpecifier("192.168.7.100")
		            .addHostSpecifier("192.168.3.66")
		            .build()
		            .send(openvasHost, openvasHostPort)
		            .getResourceId();

		    System.out.println("created new target... " + newtarget);

		    String configuration =
		        OpenvasClient.LIST_CONFIGURATIONS
		            .builder()
		            .withAuthentication(user, pass)
		            .build()
		            .send(openvasHost, openvasHostPort)
		            .getConfigurations()
		            .stream()
		            .filter(f -> f.getName().startsWith("Full and fast"))
		            .collect(Collectors.toList())
		            .get(0)
		            .getConfigurationId();

		    System.out.println("selected configuration... " + configuration);

		    String task =
		        OpenvasClient.CREATE_TASK
		            .builder()
		            .withAuthentication(user, pass)
		            .setName(rand)
		            .setConfiguration(configuration)
		            .setTarget(newtarget)
		            .build()
		            .send(openvasHost, openvasHostPort)
		            .getResourceId();

		    System.out.println("created task... " + task);

		    System.out.println(
		        OpenvasClient.RUN_TASK
		            .builder()
		            .withAuthentication(user, pass)
		            .setTask(task)
		            .build()
		            .send(openvasHost, openvasHostPort));
		  }
}
