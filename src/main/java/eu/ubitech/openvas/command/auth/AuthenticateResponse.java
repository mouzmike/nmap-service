package eu.ubitech.openvas.command.auth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eu.ubitech.openvas.command.Response;

@XmlRootElement(name = "authenticate_response")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AuthenticateResponse extends Response {}
