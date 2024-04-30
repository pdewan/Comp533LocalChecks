package gradingTools.comp533s20.flexible.testcases;

import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.junit.Assert;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.NotesAndScore;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533.flexible.PortNumbers;
import gradingTools.comp533s20.assignment4.Assignment4OneClientSuite;
import gradingTools.comp533s20.assignment4.Assignment4Suite;
import gradingTools.comp533s20.flexible.testcases.FlexibleStaticArgumentsTestInputGenerator;
import gradingTools.utils.RunningProjectUtils;
import util.trace.Tracer;

public class FlexibleStaticArgumentsTestCase extends PassFailJUnitTestCase {
	private static final int HEADLESS_RUNTIME = 7;
	private static final int GUI_RUNTIME = 20;
	private static final int INTER_TEST_DELAY = 5000;

//	private static final int TEST_PORT_NIO_START = 5000;
//	private static final int TEST_PORT_NIO_END = 6000;
//	private static final int TEST_PORT_RMI_START = 6000;
//	private static final int TEST_PORT_RMI_END = 7000;
//	private static final int TEST_PORT_GIPC_START = 7000;
//	private static final int TEST_PORT_GIPC_END = 8000;
	
	private static final int TEST_PORT_NIO_START = PortNumbers.getTestPortNIOStart();
	private static final int TEST_PORT_NIO_END = PortNumbers.getTestPortNIOEnd();
	private static final int TEST_PORT_RMI_START = PortNumbers.getTestPortRMIStart();
	private static final int TEST_PORT_RMI_END = PortNumbers.getTestPortRMIEnd();
	private static final int TEST_PORT_GIPC_START = PortNumbers.getTestPortGIPCStart();
	private static final int TEST_PORT_GIPC_END = PortNumbers.getTestPortGIPCEnd();
	
	private static final String DEFAULT_HOST = "localhost";
	private static final String DEFAULT_PORT_NIO = ""+ServerPort.SERVER_PORT;
	private static final String DEFAULT_NAME = ClientArgsProcessor.DEFAULT_CLIENT_NAME;
	private static final String DEFAULT_HEADLESS = "true";
	private static final String DEFAULT_PORT_RMI = ""+Registry.REGISTRY_PORT;
	private static final String DEFAULT_PORT_GIPC = ""+ServerPort.GIPC_SERVER_PORT;
	
	public static final List<String> DEFAULT_CLIENT_ARGS = Arrays.asList(DEFAULT_HOST, DEFAULT_PORT_NIO, DEFAULT_NAME, DEFAULT_HEADLESS, DEFAULT_HOST, DEFAULT_PORT_RMI, DEFAULT_PORT_GIPC);
	public static final List<String> DEFAULT_SERVER_ARGS = Arrays.asList(DEFAULT_PORT_NIO, DEFAULT_HOST, DEFAULT_PORT_RMI, DEFAULT_PORT_GIPC);
	public static final List<String> DEFAULT_REGISTRY_ARGS = Arrays.asList(DEFAULT_PORT_RMI, DEFAULT_PORT_RMI, DEFAULT_PORT_RMI);
	
	private static final String TEST_HOST = "classroom.cs.unc.edu";
	private static final String TEST_HOST_WITH_GIPC = DEFAULT_HOST;//classroom.cs.unc.edu";
	private static int TEST_PORT_NIO_INT = TEST_PORT_NIO_START;
	private static String TEST_PORT_NIO = ""+TEST_PORT_NIO_INT;
	private static final String TEST_NAME = "test";
	private static final String TEST_HEADLESS = "true";
	private static int TEST_PORT_RMI_INT = TEST_PORT_RMI_START;
	private static String TEST_PORT_RMI = ""+TEST_PORT_RMI_INT;
	private static int TEST_PORT_GIPC_INT = TEST_PORT_GIPC_START;
	private static String TEST_PORT_GIPC = ""+TEST_PORT_GIPC_INT;

	public static List<String>[] getTestArgs() {
		incrementPorts(true, true, true);
		@SuppressWarnings("unchecked")
		List<String>[] ret = new List[] {getTestClient0Args(), getTestClient1Args(), getTestServerArgs(), getTestRegistryArgs()};
		return ret;
	}
	
	public static List<String> getTestClient0Args() {
		return Arrays.asList(DEFAULT_HOST, TEST_PORT_NIO, TEST_0_NAME, DEFAULT_HEADLESS, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	}
	
	public static List<String> getTestClient1Args() {
		return Arrays.asList(DEFAULT_HOST, TEST_PORT_NIO, TEST_0_NAME, DEFAULT_HEADLESS, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	}
	
	public static List<String> getTestServerArgs() {
		return Arrays.asList(TEST_PORT_NIO, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	}
	
	public static List<String> getTestRegistryArgs() {
		return Arrays.asList(TEST_PORT_RMI);
	}
	
	private static void incrementPorts(boolean nio, boolean rmi, boolean gipc) {
		if (nio) {
			TEST_PORT_NIO_INT ++;
			if (TEST_PORT_NIO_INT == TEST_PORT_NIO_END) {
				TEST_PORT_NIO_INT = TEST_PORT_NIO_START;
			}
			TEST_PORT_NIO = ""+TEST_PORT_NIO_INT;
		}
		if (rmi) {
			TEST_PORT_RMI_INT ++;
			if (TEST_PORT_RMI_INT == TEST_PORT_RMI_END) {
				TEST_PORT_RMI_INT = TEST_PORT_RMI_START;
			}
			TEST_PORT_RMI = ""+TEST_PORT_RMI_INT;
		}
		if (gipc) {
			TEST_PORT_GIPC_INT ++;
			if (TEST_PORT_GIPC_INT == TEST_PORT_GIPC_END) {
				TEST_PORT_GIPC_INT = TEST_PORT_GIPC_START;
			}
			TEST_PORT_GIPC = ""+TEST_PORT_GIPC_INT;
		}
	}

	public static final String TEST_0_NAME = "Client_0";
	public static final String TEST_1_NAME = "Client_1";

	public static final List<String> TEST_CLIENT_0_ARGS = Arrays.asList(DEFAULT_HOST, TEST_PORT_NIO, TEST_0_NAME, DEFAULT_HEADLESS, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	public static final List<String> TEST_CLIENT_1_ARGS = Arrays.asList(DEFAULT_HOST, TEST_PORT_NIO, TEST_1_NAME, DEFAULT_HEADLESS, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	public static final List<String> TEST_SERVER_ARGS = Arrays.asList(TEST_PORT_NIO, DEFAULT_HOST, TEST_PORT_RMI, TEST_PORT_GIPC);
	public static final List<String> TEST_REGISTRY_ARGS = Arrays.asList(TEST_PORT_RMI, TEST_PORT_RMI, TEST_PORT_RMI);

	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	
	public FlexibleStaticArgumentsTestCase(boolean doNIO, boolean doRMI, boolean doGIPC) {
//		super("Static arguments test case");

		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
	}
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		int correct = 0;
		int possible = 6;
		StringBuilder message = new StringBuilder();
		int[] scoring = new int[]{0,0};
		try {
//			runCheck(project, message, scoring, "", "", "", "", "", "", "", "", "", "", "");
//			runCheck(project, message, scoring, "", "", "", "", "", "", "", TEST_HEADLESS, "", "", "");
//			testHeadless(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
			checkIndividual(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
//			runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
			
//			if (doNIO) {
//				runCheck(project, message, scoring, TEST_PORT_NIO, "", "", "", "", "", "", TEST_HEADLESS, "", "", "");
//				if (doGIPC) {
//					runCheck(project, message, scoring, "", "", "", "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, TEST_PORT_NIO, "", "", "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, "", TEST_HEADLESS, "", "", "");
//				} else {
//					runCheck(project, message, scoring, "", "", "", "", TEST_HOST, TEST_PORT_NIO, "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, TEST_PORT_NIO, "", "", "", TEST_HOST, TEST_PORT_NIO, "", TEST_HEADLESS, "", "", "");
//				}
//			}
//			if (doRMI) {
//				if (doGIPC) {
//					runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "", "", "", "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, "", "", "", "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "");
//					runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "");
//				} else {
//					runCheck(project, message, scoring, "", TEST_HOST, TEST_PORT_RMI, "", "", "", "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, "", "", "", "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST, TEST_PORT_RMI, "");
//					runCheck(project, message, scoring, "", TEST_HOST, TEST_PORT_RMI, "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST, TEST_PORT_RMI, "");
//				}
//			}
//			if (doGIPC) {
//				runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC, "", "", "", TEST_HEADLESS, "", "", "");
//				runCheck(project, message, scoring, "", "", "", "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC);
//				runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC, "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC);
//			}
//			if (doNIO && doRMI) {
//				if (doGIPC) {
//					runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "", "", "", "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, "", "", "", "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "");
//					runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "");
//				} else {
//					runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST, TEST_PORT_RMI, "", "", "", "", TEST_HEADLESS, "", "", "");
//					runCheck(project, message, scoring, "", "", "", "", TEST_HOST, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, "");
//					runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST, TEST_PORT_RMI, "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST, TEST_PORT_RMI, "");
//				}
//			}
//			if (doNIO && doGIPC) {
//				runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC, "", "", "", TEST_HEADLESS, "", "", "");
//				runCheck(project, message, scoring, "", "", "", "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC);
//				runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC, TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, "", TEST_PORT_GIPC);
//			}
//			if (doRMI && doGIPC) {
//				runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, "", "", "", TEST_HEADLESS, "", "", "");
//				runCheck(project, message, scoring, "", "", "", "", "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
//				runCheck(project, message, scoring, "", TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, "", "", TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
//			}
//			if (doNIO && doRMI && doGIPC) {
//				runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, "", "", "", TEST_HEADLESS, "", "", "");
//				runCheck(project, message, scoring, "", "", "", "", TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
//				runCheck(project, message, scoring, TEST_PORT_NIO, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC, TEST_HOST_WITH_GIPC, TEST_PORT_NIO, TEST_NAME, TEST_HEADLESS, TEST_HOST_WITH_GIPC, TEST_PORT_RMI, TEST_PORT_GIPC);
//			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		correct = scoring[0];
		possible = scoring[1];
		if (correct == possible) {
			return pass();
		} else if (correct != 0) {
			return partialPass(((double)correct)/(possible), message.toString());
		} else {
			return fail(message.toString());
		}
	}
	
	private void testHeadless(Project project, StringBuilder message, int[] scoring, String testServerNIOPort, String testServerRegistryHost, String testServerRMIPort, String testServerGIPCPort, String testNIOHost, String testClientNIOPort, String testName, String testClientRegistryHost, String testClientRMIPort, String testClientGIPCPort) throws NotRunnableException {
		
		String runHeadless = "false";
		setupProcesses(new String[] {testServerNIOPort, testServerRegistryHost, testServerRMIPort, testServerGIPCPort}, new String[] {testNIOHost, testClientNIOPort, testName, runHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort}, doNIO, doRMI, doGIPC);

		// Get the output when we have no input from the user
		RunningProject interactiveInputProject = null;
		FlexibleStaticArgumentsTestInputGenerator inputGenerator = new FlexibleStaticArgumentsTestInputGenerator(doNIO, doRMI, doGIPC);
		try {
			interactiveInputProject = RunningProjectUtils.runProject(project, /*5*/ Assignment4Suite.getProcessTimeOut(), inputGenerator);
			interactiveInputProject.await();
		} catch (Exception e){
			if (!(e instanceof TimeoutException)) {
				message.append("Couldn't run code.");
			}
		}
		scoring[1]++;
		if (!inputGenerator.isHeadless()) {
			scoring[0]++;
		} else {
			if (message.length() > 0) {
				message.append(";\n");
			}
			message.append("Ran headless with headless = false");
		}
		
		runHeadless = "true";
		setupProcesses(new String[] {testServerNIOPort, testServerRegistryHost, testServerRMIPort, testServerGIPCPort}, new String[] {testNIOHost, testClientNIOPort, testName, runHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort}, doNIO, doRMI, doGIPC);

		// Get the output when we have no input from the user
		interactiveInputProject = null;
		inputGenerator = new FlexibleStaticArgumentsTestInputGenerator(doNIO, doRMI, doGIPC);
		try {
			interactiveInputProject = RunningProjectUtils.runProject(project, /*5*/ Assignment4Suite.getProcessTimeOut(), inputGenerator);
			interactiveInputProject.await();
		} catch (Exception e){
			if (!(e instanceof TimeoutException)) {
				message.append("Couldn't run code.");
			}
		}

		scoring[1]++;
		if (inputGenerator.isHeadless()) {
			scoring[0]++;
		} else {
			if (message.length() > 0) {
				message.append(";\n");
			}
			message.append("Started GUI with headless = true");
		}
	}
	
	private void checkIndividual(Project project, StringBuilder message, int[] scoring, String testServerNIOPort, String testRegistryRMIHost, String testServerRMIPort, String testServerGIPCPort, String testNIOHost, String testClientNIOPort, String testName, String testHeadless, String testClientRegistryHost, String testClientRMIPort, String testClientGIPCPort) throws NotRunnableException {
		try {
			Thread.sleep(INTER_TEST_DELAY);
		} catch (InterruptedException e) {
			System.err.println("Interrupted at " + Thread.currentThread().getStackTrace()[0]);
			Thread.currentThread().interrupt();
		}
		String result = checkArgs(project, scoring, testServerNIOPort, testRegistryRMIHost, testServerRMIPort, testServerGIPCPort, testNIOHost, testClientNIOPort, testName, testHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort);
		if (!result.isEmpty()) {
			if (message.length() > 0) {
				message.append(";\n");
			}
			message.append(result);
		}
	}
	
	private void runCheck(Project project, StringBuilder message, int[] scoring, String testServerNIOPort, String testRegistryRMIHost, String testServerRMIPort, String testServerGIPCPort, String testNIOHost, String testClientNIOPort, String testName, String testHeadless, String testClientRegistryHost, String testClientRMIPort, String testClientGIPCPort) throws NotRunnableException {
		try {
			Thread.sleep(INTER_TEST_DELAY);
		} catch (InterruptedException e) {
			System.err.println("Interrupted at " + Thread.currentThread().getStackTrace()[0]);
			Thread.currentThread().interrupt();
		}
		int[] internalScore = {0,0};
		String result = checkArgs(project, internalScore, testServerNIOPort, testRegistryRMIHost, testServerRMIPort, testServerGIPCPort, testNIOHost, testClientNIOPort, testName, testHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort);
		scoring[1]++;
		if (result.isEmpty()) {
			scoring[0]++;
		} else {
			if (message.length() > 0) {
				message.append(";\n");
			}
			message.append(result);
		}
	}
	
	private String checkArgs(Project project, int[] scoring, String testServerNIOPort, String testServerRegistryHost, String testServerRMIPort, String testServerGIPCPort, String testNIOHost, String testClientNIOPort, String testName, String testHeadless, String testClientRegistryHost, String testClientRMIPort, String testClientGIPCPort) throws NotRunnableException{
		String serverCallSetup = buildServerArgDescriptor(testServerNIOPort, testServerRegistryHost, testServerRMIPort, testServerGIPCPort);
		String clientCallSetup = buildClientArgDescriptor(testNIOHost, testClientNIOPort, testName, testHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort);
		
		int serverArgCount = serverArgCount(testServerNIOPort, testServerRegistryHost, testServerRMIPort, testServerGIPCPort);
		int clientArgCount = clientArgCount(testNIOHost, testClientNIOPort, testName, testHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort);
		
		if (!testServerGIPCPort.isEmpty() && testServerRMIPort.isEmpty()) {
			testServerRMIPort = DEFAULT_PORT_RMI;
		}
		if (!testServerRMIPort.isEmpty() && testServerRegistryHost.isEmpty()) {
			testServerRegistryHost = DEFAULT_HOST;
		}
		if (!testServerRegistryHost.isEmpty() && testServerNIOPort.isEmpty()) {
			testServerNIOPort = DEFAULT_PORT_NIO;
		}
		
		if (!testClientGIPCPort.isEmpty() && testClientRMIPort.isEmpty()) {
			testClientRMIPort = DEFAULT_PORT_RMI;
		}
		if (!testClientRMIPort.isEmpty() && testClientRegistryHost.isEmpty()) {
			testClientRegistryHost = DEFAULT_HOST;
		}
		if (!testClientRegistryHost.isEmpty() && testHeadless.isEmpty()) {
			testHeadless = DEFAULT_HEADLESS;
		}
		if (!testHeadless.isEmpty() && testName.isEmpty()) {
			testName = DEFAULT_NAME;
		}
		if (!testName.isEmpty() && testClientNIOPort.isEmpty()) {
			testClientNIOPort = DEFAULT_PORT_NIO;
		}
		if (!testClientNIOPort.isEmpty() && testNIOHost.isEmpty()) {
			testNIOHost = DEFAULT_HOST;
		}
		
		StringBuilder message = new StringBuilder();
		StringBuilder nioMessageClient = new StringBuilder();
		StringBuilder rmiMessageClient = new StringBuilder();
		StringBuilder gipcMessageClient = new StringBuilder();
		StringBuilder nioMessageServer = new StringBuilder();
		StringBuilder rmiMessageServer = new StringBuilder();
		StringBuilder gipcMessageServer = new StringBuilder();
		setupProcesses(new String[] {testServerNIOPort, testServerRegistryHost, testServerRMIPort, testServerGIPCPort}, new String[] {testNIOHost, testClientNIOPort, testName, testHeadless, testClientRegistryHost, testClientRMIPort, testClientGIPCPort}, doNIO, doRMI, doGIPC);

		// Get the output when we have no input from the user
		RunningProject interactiveInputProject = null;
		FlexibleStaticArgumentsTestInputGenerator inputGenerator = new FlexibleStaticArgumentsTestInputGenerator(doNIO, doRMI, doGIPC);
		try {
			boolean shouldNotDisplayGUI = Boolean.parseBoolean(testHeadless) || Boolean.parseBoolean(System.getProperty("java.awt.headless"));
			int runtime = shouldNotDisplayGUI ? HEADLESS_RUNTIME : GUI_RUNTIME;
			interactiveInputProject = RunningProjectUtils.runProject(project, /*runtime*/ Assignment4Suite.getProcessTimeOut(), inputGenerator);
			interactiveInputProject.await();
		} catch (Exception e){
			if (!(e instanceof TimeoutException)) {
				Assert.assertTrue("Couldn't run code:" + e.getMessage() + ":" + NotesAndScore.PERCENTAGE_MARKER + 0, false);

//				return "Couldn't run code.";
			}
		}
		if (interactiveInputProject != null) {
			interactiveInputProject.getProcessOutput().forEach((name, output) -> Tracer.info(this, "*** " + name + " ***\n" + output));
			if (inputGenerator.didNIO()) {
				scoring[1]+=3; // client host, port + server port
				if (!inputGenerator.foundClientNIOInfo()) {
					nioMessageClient.append("Couldn't find client NIO connection info in traces");
				} else {
					String[] info = inputGenerator.getClientNIOInfo();
					boolean isCorrect = testNIOHost.equals(info[0]) || testNIOHost.equals(info[1])
							|| (testNIOHost.isEmpty() && (DEFAULT_HOST.equals(info[0]) || DEFAULT_HOST.equals(info[1]))); 
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(nioMessageClient, "Client", isCorrect, "nio-host", 1, clientArgCount);
					isCorrect = testClientNIOPort.equals(info[2])
							|| (testClientNIOPort.isEmpty() && DEFAULT_PORT_NIO.equals(info[2]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(nioMessageClient, "Client", isCorrect, "nio-port", 2, clientArgCount);
				}
				if (!inputGenerator.foundServerNIOInfo()) {
					nioMessageServer.append("Couldn't find server NIO connection info in traces");
				} else {
					String[] info = inputGenerator.getServerNIOInfo();
					boolean isCorrect = testServerNIOPort.equals(info[2])
							|| (testServerNIOPort.isEmpty() && DEFAULT_PORT_NIO.equals(info[2]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(nioMessageServer, "Server", isCorrect, "nio-port", 1, serverArgCount);
				}
			}
			if (inputGenerator.didRMI()) {
				scoring[1] += 4; // client host, port + server host+port
				if (!inputGenerator.foundClientRMIInfo()) {
					rmiMessageClient.append("Couldn't find client RMI connection info in traces");
				} else {
					String[] info = inputGenerator.getClientRMIInfo();
					boolean isCorrect = testClientRegistryHost.equals(info[0])
							|| (testClientRegistryHost.isEmpty() && !testNIOHost.isEmpty() && testNIOHost.equals(info[0]))
							|| (testClientRegistryHost.isEmpty() && testNIOHost.isEmpty() && DEFAULT_HOST.equals(info[0]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(rmiMessageClient, "Client", isCorrect, "registry-host", 5, clientArgCount);
					isCorrect = testClientRMIPort.equals(info[1])
							|| (testClientRMIPort.isEmpty() && DEFAULT_PORT_RMI.equals(info[1]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(rmiMessageClient, "Client", isCorrect, "registry-port", 6, clientArgCount);
				}
				if (!inputGenerator.foundServerRMIInfo()) {
					rmiMessageServer.append("Couldn't find server RMI connection info in traces");
				} else {
					String[] info = inputGenerator.getServerRMIInfo();
					boolean isCorrect = testServerRegistryHost.equals(info[0])
							|| (testServerRegistryHost.isEmpty() && DEFAULT_HOST.equals(info[0]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(rmiMessageServer, "Server", isCorrect, "registry-host", 2, serverArgCount);
					isCorrect = testServerRMIPort.equals(info[1])
							|| (testServerRMIPort.isEmpty() && DEFAULT_PORT_RMI.equals(info[1]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(rmiMessageServer, "Server", isCorrect, "registry-port", 3, serverArgCount);
				}
			}
			if (inputGenerator.didGIPC()) {
				scoring[1] += 4; // client host, port, name + server port
				if (!inputGenerator.foundClientGIPCInfo()) {
					gipcMessageClient.append("Couldn't find client GIPC connection info in traces");
				} else {
					String[] info = inputGenerator.getClientGIPCInfo();
					boolean isCorrect = testClientRegistryHost.equals(info[0])
							|| (testClientRegistryHost.isEmpty() && !testNIOHost.isEmpty() && testNIOHost.equals(info[0]))
							|| (testClientRegistryHost.isEmpty() && testNIOHost.isEmpty() && DEFAULT_HOST.equals(info[0]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(gipcMessageClient, "Client", isCorrect, "registry-host", 5, clientArgCount);
					isCorrect = testClientGIPCPort.equals(info[1])
							|| (testClientGIPCPort.isEmpty() && DEFAULT_PORT_GIPC.equals(info[1]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(gipcMessageClient, "Client", isCorrect, "gipc-port", 7, clientArgCount);
					isCorrect = testName.equals(info[2])
							|| (testName.isEmpty() && DEFAULT_NAME.equals(info[2]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(gipcMessageClient, "Client", isCorrect, "name", 3, clientArgCount);
				}
				if (!inputGenerator.foundServerGIPCInfo()) {
					gipcMessageServer.append("Couldn't find server GIPC connection info in traces");
				} else {
					String[] info = inputGenerator.getServerGIPCInfo();
					boolean isCorrect = testServerGIPCPort.equals(info[0])
							|| (testServerGIPCPort.isEmpty() && DEFAULT_PORT_GIPC.equals(info[0]));
					if (isCorrect) {
						scoring[0] ++;
					}
					appendMessage(gipcMessageServer, "Server", isCorrect, "gipc-port", 4, serverArgCount);
				}
			}
		}
		boolean hasClientErrors = false;
		boolean hasServerErrors = false;
		StringBuilder clientMessage = new StringBuilder();
		StringBuilder serverMessage = new StringBuilder();
		clientMessage.append("Error in client (").append(clientCallSetup).append("): ");
		serverMessage.append("Error in server (").append(serverCallSetup).append("): ");
		if (nioMessageClient.length() != 0) {
			clientMessage.append(nioMessageClient);
			hasClientErrors = true;
		}
		if (rmiMessageClient.length() != 0) {
			if (hasClientErrors) {
				clientMessage.append(" + ");
			}
			clientMessage.append(rmiMessageClient);
			hasClientErrors = true;
		}
		if (gipcMessageClient.length() != 0) {
			if (hasClientErrors) {
				clientMessage.append(" + ");
			}
			clientMessage.append(gipcMessageClient);
			hasClientErrors = true;
		}
		if (nioMessageServer.length() != 0) {
			serverMessage.append(nioMessageServer);
			hasServerErrors = true;
		}
		if (rmiMessageServer.length() != 0) {
			if (hasServerErrors) {
				serverMessage.append(" + ");
			}
			serverMessage.append(rmiMessageServer);
			hasServerErrors = true;
		}
		if (gipcMessageServer.length() != 0) {
			if (hasServerErrors) {
				serverMessage.append(" + ");
			}
			serverMessage.append(gipcMessageServer);
			hasServerErrors = true;
		}
		if (hasClientErrors) {
			message.append(clientMessage).append(".");
		}
		if (hasServerErrors) {
			if (hasClientErrors) {
				message.append(" ");
			}
			message.append(serverMessage).append(".");
		}
		return message.toString();
	}
	
	private static String buildServerArgDescriptor(String testServerPort, String testServerRMIHost, String testServerRMIPort, String testServerGIPCPort) {
		if (!testServerGIPCPort.isEmpty()) {
			return "args as 'nio-port registry-host registry-port gipc-port'";
		}
		if (!testServerRMIPort.isEmpty()) {
			return "args as 'nio-port registry-host registry-port'";
		}
		if (!testServerRMIHost.isEmpty()) {
			return "args as 'nio-port registry-host'";
		}
		if (!testServerPort.isEmpty()) {
			return "args as 'nio-port'";
		}
		return "no args";
	}
	
	private static String buildClientArgDescriptor(String testHost, String testClientPort, String testName, String testHeadless, String testClientRMIHost, String testClientRMIPort, String testClientGIPCPort) {
		if (!testClientGIPCPort.isEmpty()) {
			return "args as 'nio-host nio-port name headless registry-host registry-port gipc-port'";
		}
		if (!testClientRMIPort.isEmpty()) {
			return "args as 'nio-host nio-port name headless registry-host registry-port'";
		}
		if (!testClientRMIHost.isEmpty()) {
			return "args as 'nio-host nio-port name headless registry-host'";
		}
		if (!testHeadless.isEmpty()) {
			return "args as 'nio-host nio-port name headless'";
		}
		if (!testName.isEmpty()) {
			return "args as 'nio-host nio-port name'";
		}
		if (!testClientPort.isEmpty()) {
			return "args as 'nio-host nio-port'";
		}
		if (!testHost.isEmpty()) {
			return "args as 'nio-host'";
		}
		return "no args";
	}
	
	private static int serverArgCount(String testServerPort, String testServerRMIHost, String testServerRMIPort, String testServerGIPCPort) {
		if (!testServerGIPCPort.isEmpty()) {
			return 4;
		}
		if (!testServerRMIPort.isEmpty()) {
			return 3;
		}
		if (!testServerRMIHost.isEmpty()) {
			return 2;
		}
		if (!testServerPort.isEmpty()) {
			return 1;
		}
		return 0;
	}
	
	private static int clientArgCount(String testHost, String testClientPort, String testName, String testHeadless, String testClientRMIHost, String testClientRMIPort, String testClientGIPCPort) {
		if (!testClientGIPCPort.isEmpty()) {
			return 7;
		}
		if (!testClientRMIPort.isEmpty()) {
			return 6;
		}
		if (!testClientRMIHost.isEmpty()) {
			return 5;
		}
		if (!testHeadless.isEmpty()) {
			return 4;
		}
		if (!testName.isEmpty()) {
			return 3;
		}
		if (!testClientPort.isEmpty()) {
			return 2;
		}
		if (!testHost.isEmpty()) {
			return 1;
		}
		return 0;
	}
	
	private static StringBuilder appendMessage(StringBuilder message, String source, boolean correct, String part, int argPos, int argCount) {
		if (!correct) {
			if (argPos > argCount) {
				if (message.length() == 0) {
					message.append(source + " not using default " + part);
				} else {
					message.append(", default " + part);
				}
			} else {
				if (message.length() == 0) {
					message.append(source + " not using provided " + part);
				} else {
					message.append(", provided " + part);
				}
			}
		}
		return message;
	}
	
	private static void setupProcesses(String[] serverArgs, String[] clientArgs, boolean doNIO, boolean doRMI, boolean doGIPC) {
		Assignment4OneClientSuite.oneClientSetupProcesses(serverArgs, clientArgs, doNIO, doRMI, doGIPC);
//		List<String> serverArgList = Arrays.stream(serverArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
//		List<String> registryArgList = (serverArgList.size() >= 3 && !serverArgList.get(2).equals(DEFAULT_PORT_RMI)) ? serverArgList.subList(2, 3) : Collections.emptyList();
//		List<String> clientArgList = Arrays.stream(clientArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
//		
////		serverArgList.removeIf(s-> s.isEmpty());
////		clientArgList.removeIf(s-> s.isEmpty());
//		
//		if (doRMI) {
//			ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Registry", registryArgList);
//			ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Registry", 500);
//		} else {
//			ExecutionSpecificationSelector.getExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
//			ExecutionSpecificationSelector.getExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
//		}
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Server", serverArgList);
//		ExecutionSpecificationSelector.getExecutionSpecification().setArgs("Client", clientArgList);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Server", 1000);
//		ExecutionSpecificationSelector.getExecutionSpecification().setSleepTime("Client", 2000);
//		ExecutionSpecificationSelector.getExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}
}
