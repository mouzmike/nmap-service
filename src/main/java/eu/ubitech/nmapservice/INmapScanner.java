package eu.ubitech.nmapservice;

import org.springframework.stereotype.Service;

import eu.ubitech.nmapservice.exception.InvalidNmapHostException;
import eu.ubitech.nmapservice.exception.NoHostsSetException;

@Service
public interface INmapScanner {
  String initiateScan(NmapRequest request) throws NoHostsSetException, InvalidNmapHostException;
}
