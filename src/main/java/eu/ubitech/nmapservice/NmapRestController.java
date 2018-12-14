package eu.ubitech.nmapservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.ubitech.nmapservice.exception.InvalidNmapHostException;
import eu.ubitech.nmapservice.exception.NoHostsSetException;

@RestController
@RequestMapping("nmap")
public class NmapRestController {

  @Autowired private INmapScanner nmapScanner;

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  public String createNmapScanJob(@RequestBody NmapRequest request)
      throws NoHostsSetException, InvalidNmapHostException {
    return nmapScanner.initiateScan(request);
  }
}
