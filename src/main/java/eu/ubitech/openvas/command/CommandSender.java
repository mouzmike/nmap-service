package eu.ubitech.openvas.command;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import eu.ubitech.openvas.command.auth.AuthenticateRequest;
import eu.ubitech.openvas.command.auth.AuthenticateResponse;
import eu.ubitech.openvas.exceptions.OpenvasClientAuthenticationException;
import eu.ubitech.openvas.exceptions.OpenvasClientException;
import eu.ubitech.openvas.exceptions.OpenvasCommandExecutionException;

public abstract class CommandSender<Req, Res extends Response> {

  private static final Pattern HTTP_CODE_SUCCESS_PATTERN = Pattern.compile("2\\d{2}");
  private static final JAXB _JAXB = JAXB.create();
  protected AuthenticatedRequest<?> _this;
  

  public Res send(final String host, final int port)
      throws OpenvasClientAuthenticationException, OpenvasCommandExecutionException,
          OpenvasClientException {
    Objects.requireNonNull(this._this);
    Objects.requireNonNull(_JAXB);

    try (Socket openvasClient =
        javax.net.ssl.SSLSocketFactory.getDefault().createSocket(host, port)) {

      _JAXB.marsaller.marshal(this._this, openvasClient.getOutputStream());

      openvasClient.getOutputStream().flush();

      byte[] buffer = new byte[1024];
      byte[] chunk = new byte[128];

      DataInputStream dis = new DataInputStream(openvasClient.getInputStream());
      int bytesRead = 0, totalBytesRead = 0;


      while ((bytesRead = dis.read(chunk)) > 0) {

        if (totalBytesRead + bytesRead > buffer.length) {
          byte[] tmp = new byte[buffer.length * 2];
          System.arraycopy(buffer, 0, tmp, 0, totalBytesRead);
          buffer = tmp;
        }

        System.arraycopy(chunk, 0, buffer, totalBytesRead, bytesRead);

        totalBytesRead += bytesRead;

        if (bytesRead < chunk.length) {
          break;
        }
      }
      
      byte[] finalArray = new byte[totalBytesRead];
      System.arraycopy(buffer, 0, finalArray, 0, totalBytesRead);
      System.out.println(new String(finalArray));
      StringReader sr = new StringReader(new String(finalArray));

      AuthenticatedResponse<? extends Response> response =
          (AuthenticatedResponse<? extends Response>) _JAXB.unmarshaller.unmarshal(sr);

      if (!HTTP_CODE_SUCCESS_PATTERN.matcher(String.valueOf(response.status)).matches()) {
        throw new OpenvasCommandExecutionException(response.text);
      }

      if (!HTTP_CODE_SUCCESS_PATTERN.matcher(String.valueOf(response.status)).matches()) {
        throw new OpenvasClientAuthenticationException(response.text);
      }

      Object commandResponse = response.getCommandResponse();

      if (!HTTP_CODE_SUCCESS_PATTERN.matcher(String.valueOf(((Response) commandResponse).status)).matches() ){
        throw new OpenvasCommandExecutionException(((Response) commandResponse).text);
      }

      return (Res) response.getCommandResponse();

    } catch (IOException | JAXBException e) {
      throw new OpenvasClientException(e);
    }
  }

  static final class JAXB {

    final Marshaller marsaller;
    final Unmarshaller unmarshaller;

    static final JAXB create() {
      try {
        return new JAXB();
      } catch (JAXBException e) {
    	 e.printStackTrace();
        return null;
      }
    }

    private JAXB() throws JAXBException {
      final JAXBContext context =
          JAXBContext.newInstance(
              AuthenticatedRequest.class,
              AuthenticatedResponse.class,
              CreateTargetCommand.CreateTargetRequest.class,
              CreateTargetCommand.CreateTargetResponse.class,
              AuthenticateRequest.class,
              AuthenticateResponse.class,
              GetConfigurationsCommand.GetConfigurationsRequest.class,
              GetConfigurationsCommand.GetConfigurationsResponse.class,
              CreateTaskCommand.CreateTaskRequest.class,
              CreateTaskCommand.CreateTaskResponse.class,
              ResourceResponse.class,
              StartTaskCommand.StartTaskRequest.class,
              StartTaskCommand.StartTaskResponse.class,
              Response.class);
      this.marsaller = context.createMarshaller();
      this.unmarshaller = context.createUnmarshaller();
    }
  }
}
