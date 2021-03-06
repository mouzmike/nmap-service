package eu.ubitech.openvas;

import eu.ubitech.openvas.command.CreateTargetCommand;
import eu.ubitech.openvas.command.CreateTaskCommand;
import eu.ubitech.openvas.command.GetConfigurationsCommand;
import eu.ubitech.openvas.command.GetResultsCommand;
import eu.ubitech.openvas.command.GetTaskStatusCommand;
import eu.ubitech.openvas.command.StartTaskCommand;

public class OpenvasClient {
	
	public static final CreateTargetCommand CREATE_TARGET = new CreateTargetCommand();
	public static final GetConfigurationsCommand LIST_CONFIGURATIONS = new GetConfigurationsCommand();
	public static final CreateTaskCommand CREATE_TASK = new CreateTaskCommand();
	public static final StartTaskCommand RUN_TASK = new StartTaskCommand();
	public static final GetResultsCommand GET_RESULTS = new GetResultsCommand();
	public static final GetTaskStatusCommand TASK_STATUS = new GetTaskStatusCommand();
}
