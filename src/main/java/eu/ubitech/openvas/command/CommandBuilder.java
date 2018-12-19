package eu.ubitech.openvas.command;

public interface CommandBuilder<Req extends Request> {
	
	CommandBuilder<Req> build();
		
}
