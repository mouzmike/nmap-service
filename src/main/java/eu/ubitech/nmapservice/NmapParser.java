package eu.ubitech.nmapservice;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eu.ubitech.nmapservice.exception.NmapOutputParsingException;

public class NmapParser implements INmapParser {

  @Override
  public NmapResult parse(String filePath) throws NmapOutputParsingException {

    try {
      JAXBContext s = JAXBContext.newInstance(NmapResult.class);
      Unmarshaller un = s.createUnmarshaller();
      return (NmapResult) un.unmarshal(new File(filePath));
    } catch (JAXBException e) {
      throw new NmapOutputParsingException(e);
    }
  }
}
