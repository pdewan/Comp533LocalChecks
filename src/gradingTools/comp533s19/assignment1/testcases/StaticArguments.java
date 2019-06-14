package gradingTools.comp533s19.assignment1.testcases;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s19.assignment1.Assignment1OneClientSuite;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
import gradingTools.utils.RunningProjectUtils;
import util.annotations.MaxValue;
import util.trace.Tracer;
@MaxValue(20)
public class StaticArguments extends PassFailJUnitTestCase {
	public static final List<String> DEFAULT_CLIENT_ARGS = Arrays.asList("localhost", ""+ServerPort.SERVER_PORT, ClientArgsProcessor.DEFAULT_CLIENT_NAME, "true");
	public static final List<String> DEFAULT_SERVER_ARGS = Arrays.asList(""+ServerPort.SERVER_PORT, "localhost");

	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_PORT = ""+ServerPort.SERVER_PORT;
	
	private static final String TEST_HOST = "classroom.cs.unc.edu";
	private static final String TEST_PORT = "4242";
	
	
	public StaticArguments() {
//		super("Prompt printer test case");
//		super("Static arguments test case");

	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		int correct = 0;
		int possible = 6;
		StringBuilder message = new StringBuilder();
		try {
			String result = checkArgs(project, "", "", "");
			if (result.isEmpty()) {
				correct++;
			} else {
				message.append(result);
			}
			result = checkArgs(project, "", TEST_HOST, "");
			if (result.isEmpty()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append("\n");
				}
				message.append(result);
			}
			result = checkArgs(project, "", TEST_HOST, TEST_PORT);
			if (result.isEmpty()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append("\n");
				}
				message.append(result);
			}
			result = checkArgs(project, TEST_PORT, "", "");
			if (result.isEmpty()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append("\n");
				}
				message.append(result);
			}
			result = checkArgs(project, TEST_PORT, TEST_HOST, "");
			if (result.isEmpty()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append("\n");
				}
				message.append(result);
			}
			result = checkArgs(project, TEST_PORT, TEST_HOST, TEST_PORT);
			if (result.isEmpty()) {
				correct++;
			} else {
				if (message.length() > 0) {
					message.append("\n");
				}
				message.append(result);
			}
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if (correct == possible) {
			return pass();
		} else if (correct != 0) {
			return partialPass(((double)correct)/(possible), message.toString());
		} else {
			return fail(message.toString());
		}
	}
	
	private String checkArgs(Project project, String testServerPort, String testHost, String testClientPort) throws NotRunnableException{
		StringBuilder message = new StringBuilder();
		if (testServerPort.isEmpty()) {
			testServerPort = DEFAULT_PORT;
		}
		if (testHost.isEmpty()) {
			testHost = DEFAULT_HOST;
		}
		if (testClientPort.isEmpty()) {
			testClientPort = DEFAULT_PORT;
		}
		setupProcesses(new String[] {testServerPort}, new String[] {testHost, testClientPort, ClientArgsProcessor.DEFAULT_CLIENT_NAME, "true"});

		// Get the output when we have no input from the user
		RunningProject interactiveInputProject = null;
		StaticArgumentsTestInputGenerator inputGenerator = new StaticArgumentsTestInputGenerator();
		try {
			interactiveInputProject = RunningProjectUtils.runProject(project, Assignment1Suite.getProcessTimeOut(), inputGenerator);
			String incOutput = interactiveInputProject.await();
		} catch (Exception e){
			if (!(e instanceof TimeoutException)) {
				return "Couldn't run code.";
			}
		}
		if (interactiveInputProject != null) {
			interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			if (!inputGenerator.foundClientInfo()) {
				message.append("Couldn't find client connection info in traces.");
			} else {
				String clientInfo = inputGenerator.getClientInfo();
				Tracer.info(this, "--- CLIENT INFO ---\n" + clientInfo);
				int portEnd = clientInfo.indexOf(',');
				int portStart = clientInfo.lastIndexOf(':', portEnd) + 1;
				int hostEnd = clientInfo.indexOf('/');
				int hostStart = clientInfo.lastIndexOf('(', hostEnd) + 1;
				if (portStart <= 0 || portEnd == -1 || hostStart <= 0 || hostEnd == -1) {
					message.append("Couldn't find client connection info in traces.");
				} else {
					String port = clientInfo.substring(portStart, portEnd);
					String host = clientInfo.substring(hostStart, hostEnd);
					
					
					Tracer.info(this, "HOST: " + host + " PORT: " + port);
					if (testHost.isEmpty()) {
						if (!host.equalsIgnoreCase(DEFAULT_HOST)) {
							message.append("Client not using default host (no args).");
						}
						if (!port.equalsIgnoreCase(DEFAULT_PORT)) {
							if (message.length() >= 0) {
								message.append(" ");
							}
							message.append("Client not using default port (no args).");
						}
					} else {
						if (!host.equalsIgnoreCase(testHost)) {
							message.append("Client not using provided host (args as 'host");
							if (!testClientPort.isEmpty()) {
								message.append(" port').");
							} else {
								message.append("').");
							}
						}
						if (testClientPort.isEmpty()) {
							if (!port.equalsIgnoreCase(DEFAULT_PORT)) {
								if (message.length() >= 0) {
									message.append(" ");
								}
								message.append("Client not using default port (args as 'host').");
							}
						} else {
							if (!port.equalsIgnoreCase(testClientPort)) {
								if (message.length() >= 0) {
									message.append(" ");
								}
								message.append("Client not using provided port (args as 'host port').");
							}
						}
					}
				}
			}
			if (!inputGenerator.foundServerInfo()) {
				if (message.length() >= 0) {
					message.append(" ");
				}
				message.append("Couldn't find server connection info in traces.");
			} else {
				
				String serverInfo = inputGenerator.getServerInfo();

				Tracer.info(this, "--- SERVER INFO ---\n" + serverInfo);
				int serverPortStart = serverInfo.indexOf(':')+1;
				int serverPortEnd = serverInfo.indexOf(' ', serverPortStart);
				
				if (serverPortStart == 0 || serverPortEnd == -1) {
					if (message.length() >= 0) {
						message.append(" ");
					}
					message.append("Couldn't find server connection info in traces.");
				} else {
					String port = serverInfo.substring(serverPortStart, serverPortEnd);
					Tracer.info(this, "PORT: " + port);
					if (testServerPort.isEmpty()) {
						if (!port.equalsIgnoreCase(DEFAULT_PORT)) {
							if (message.length() >= 0) {
								message.append(" ");
							}
							message.append("Server not using default port (no args).");
						}
					} else {
						if (!port.equalsIgnoreCase(testServerPort)) {
							if (message.length() >= 0) {
								message.append(" ");
							}
							message.append("Server not using provided port (args as 'port').");
						}
					}
				}
			}
		}
		return message.toString();
	}
	
	private static void setupProcesses(String[] serverArgs, String[] clientArgs) {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Assignment1Suite.serverTagsList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Assignment1Suite.clientTagsList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", Arrays.asList(serverArgs));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", Arrays.asList(clientArgs));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
