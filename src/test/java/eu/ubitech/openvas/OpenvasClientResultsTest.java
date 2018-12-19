package eu.ubitech.openvas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eu.ubitech.openvas.exceptions.OpenvasClientAuthenticationException;
import eu.ubitech.openvas.exceptions.OpenvasClientException;
import eu.ubitech.openvas.exceptions.OpenvasCommandExecutionException;

public class OpenvasClientResultsTest {
  public static void main(String[] args)
      throws OpenvasClientAuthenticationException, OpenvasCommandExecutionException,
          OpenvasClientException, JsonProcessingException {

    String user = "admin", pass = "admin";
    String openvasHost = "218ffb30ff7a";
    int openvasHostPort = 19390;
    String task = "c172ec7e-d5c7-4c17-b5d3-6fdd38f93258";

    OpenvasClient.TASK_STATUS
        .builder()
        .withAuthentication(user, pass)
        .forTask(task)
        .build()
        .send(openvasHost, openvasHostPort).getTasks().forEach(System.out::println);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(
        objectMapper.writeValueAsString(
            OpenvasClient.GET_RESULTS
                .builder()
                .withAuthentication(user, pass)
                .forTask(task)
                .build()
                .send(openvasHost, openvasHostPort)
                .getResults()));
  }
}
