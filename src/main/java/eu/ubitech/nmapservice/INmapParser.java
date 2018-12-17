package eu.ubitech.nmapservice;

import eu.ubitech.nmapservice.exception.NmapOutputParsingException;

public interface INmapParser {
  NmapResult parse(String filePath) throws NmapOutputParsingException;
}
