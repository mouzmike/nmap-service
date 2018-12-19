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

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(
        objectMapper.writeValueAsString(
            OpenvasClient.GET_RESULTS
                .builder()
                .withAuthentication(user, pass)
                .forTask("43ae80ae-0e5d-47a0-8998-092494218910")
                .build()
                .send(openvasHost, openvasHostPort)
                .getResults()));
  }
}
