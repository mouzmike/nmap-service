package eu.ubitech.nmapservice;

import static java.util.UUID.randomUUID;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import eu.ubitech.nmapservice.exception.InvalidNmapHostException;
import eu.ubitech.nmapservice.exception.NmapOutputParsingException;
import eu.ubitech.nmapservice.exception.NoHostsSetException;

@Component
public class NmapScanner implements INmapScanner {

  private static final Logger LOGGER = LoggerFactory.getLogger(NmapScanner.class);

  private static final String IPV4_OCTET = "(25[0-5]|2[0-4][0-9]|[0-1]{0,1}[0-9]{0,1}[0-9])";
  private static final Pattern IPV4_REGEX =
      Pattern.compile(
          IPV4_OCTET
              .concat("\\.")
              .concat(IPV4_OCTET)
              .concat("\\.")
              .concat(IPV4_OCTET)
              .concat("\\.")
              .concat(IPV4_OCTET));

  @Value("${nmap.command}")
  private String nmapCommand;

  @Value("${tmp.dir}")
  private String tempDirectory;

  @Autowired private RestTemplate restTemplate;

  @Override
  public String initiateScan(NmapRequest request)
      throws NoHostsSetException, InvalidNmapHostException {

    validate(request.getHosts());
    final String job = randomUUID().toString();

    CompletableFuture.<String>supplyAsync(
            () -> {
              final String file = tempDirectory + job + ".xml";
              final String command =
                  nmapCommand + " -oX " + file + " " + String.join(" ", request.getHosts());

              LOGGER.info("Executing command {} ...", command);

              try {
                Process proc = Runtime.getRuntime().exec(command);
                proc.waitFor();
              } catch (IOException | InterruptedException e) {
                LOGGER.error("Error while executing NMAP with message {}.", e.getMessage());
              }
              return file;
            })
        .thenAccept(
            file -> {
              if (Files.exists(Paths.get(file), LinkOption.NOFOLLOW_LINKS)) {
                LOGGER.info("Result file {} was generated.", file);
              }

              if (!stringNullOrEmpty(request.getCallbackURL())) {
                try {
                  parseResultAndCallback(
                      request.getCallbackURL(), request.getAuthorization(), file);
                } catch (IOException | NmapOutputParsingException e) {
                  LOGGER.error("Error while handling result file " + file + ".", e);
                }
              }
            });

    return job;
  }

  private final void validate(List<String> hosts)
      throws NoHostsSetException, InvalidNmapHostException {
    if (hosts == null || hosts.isEmpty()) {
      throw new NoHostsSetException();
    }

    for (String host : hosts) {
      if (!IPV4_REGEX.matcher(host).matches()) {
        throw new InvalidNmapHostException(host);
      }
    }
  }

  private final boolean stringNullOrEmpty(final String s) {
    return s == null || s.isEmpty();
  }

  private final void parseResultAndCallback(
      final String callbackURL, final String authorization, final String resultFile)
      throws IOException, NmapOutputParsingException {

    INmapParser parser = new NmapParser();
    NmapResult nmapResult = parser.parse(resultFile);

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    if (!stringNullOrEmpty(authorization)) {
      headers.add("Authorization", authorization);
    }
    headers.set("Content-Type", "application/json");

    HttpEntity<NmapResult> httpEntity = new HttpEntity<>(nmapResult, headers);

    LOGGER.info(
        "Posting {} to {} with header Authorization: {} for file {}.",
        nmapResult,
        callbackURL,
        authorization,
        resultFile);

    try {
      ResponseEntity<String> result =
          restTemplate.postForEntity(callbackURL, httpEntity, String.class);
      LOGGER.info("Successfull response {} for {}/", result.getBody(), resultFile);
    } catch (Exception e) {
      LOGGER.error("Error while callback for {} with message {}.", resultFile, e.getMessage());
    }
  }

  @SuppressWarnings("unused")
  @Deprecated
  private final String readXMLReport(String file) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
      return sb.toString();
    }
  }
}
