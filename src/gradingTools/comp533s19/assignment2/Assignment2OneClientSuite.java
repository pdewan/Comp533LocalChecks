package gradingTools.comp533s19.assignment2;

import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import gradingTools.comp533s19.assignment2.testcases.RMINIOOneClientConnection;
import gradingTools.comp533s19.assignment2.testcases.RMINIOOneClientReadWriteAtomic;
import gradingTools.comp533s19.assignment2.testcases.RMINIOOneClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment2.testcases.RMINIOStaticArguments;
import gradingTools.comp533s19.flexible.testcases.FlexibleStaticArgumentsTestCase;
import util.tags.DistributedTags;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RMINIOStaticArguments.class, RMINIOOneClientConnection.class,
		RMINIOOneClientReadWriteNonAtomic.class, RMINIOOneClientReadWriteAtomic.class

})
public class Assignment2OneClientSuite {
	private static final String DEFAULT_PORT_RMI = "" + Registry.REGISTRY_PORT;

	public static void oneClientSetupProcesses(boolean doGIPC) {
		Assignment2OneClientSuite.oneClientSetupProcesses(FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS,
				FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS, true, doGIPC);
	}

	public static void oneClientSetupProcessesRMI() {
		Assignment2OneClientSuite.oneClientSetupProcesses(FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS,
				FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS, true, false);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 5000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}

	public static void oneClientSetupProcesses(String[] serverArgs, String[] clientArgs, boolean doRMI,
			boolean doGIPC) {
		List<String> serverArgList = Arrays.stream(serverArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
//	List<String> registryArgList = (serverArgList.size() >= 3 && !serverArgList.get(2).equals(DEFAULT_PORT_RMI)) ? serverArgList.subList(2, 3) : Collections.emptyList();
		List<String> clientArgList = Arrays.stream(clientArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
//	List<String> aClientTags;
		oneClientSetupProcesses(serverArgList, clientArgList, doRMI, doGIPC);
//	List<String> aServerTags;
////	serverArgList.removeIf(s-> s.isEmpty());
////	clientArgList.removeIf(s-> s.isEmpty());
//	
//	if (doRMI) {
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList(DistributedTags.REGISTRY));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", registryArgList);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
//		aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO);
//		aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO);
//	} else if (doGIPC) {
//		aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.GIPC);
//		aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.GIPC);
//	} else {
//		aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO);
//		aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
//	}
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", aServerTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", aClientTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", serverArgList);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", clientArgList);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 1000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 2000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}

	public static void oneClientSetupProcesses(List<String> serverArgList, List<String> clientArgList, boolean doRMI,
			boolean doGIPC) {
//	List<String> serverArgList = Arrays.stream(serverArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
		List<String> registryArgList = (serverArgList.size() >= 3 && !serverArgList.get(2).equals(DEFAULT_PORT_RMI))
				? serverArgList.subList(2, 3)
				: Collections.emptyList();
//	List<String> clientArgList = Arrays.stream(clientArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
		List<String> aClientTags = null;
		List<String> aServerTags = null;
//	serverArgList.removeIf(s-> s.isEmpty());
//	clientArgList.removeIf(s-> s.isEmpty());

		if (doRMI) {
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification()
					.setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification()
					.setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification()
					.setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry",
					Arrays.asList(DistributedTags.REGISTRY));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", registryArgList);
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Registry", 500);
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO);
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO);
//		} else if (doGIPC) {
		}
		if (doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO, DistributedTags.RMI, DistributedTags.GIPC);
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO, DistributedTags.RMI,DistributedTags.GIPC);
//		} else {
		}
		if (!doRMI && !doGIPC) {
			aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.NIO);
			aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.NIO);
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification()
					.setProcessTeams(Arrays.asList("DistributedProgram"));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification()
					.setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
			BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram",
					Arrays.asList("Server", "Client"));
		}

		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", aServerTags);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", aClientTags);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", serverArgList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", clientArgList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Server", 1000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Client", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams()
				.forEach(team -> System.out.println("### " + team));
	}

	public static void setupProcesses() {
		Assignment2OneClientSuite.oneClientSetupProcesses(FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS,
				FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS, true, false);
	}
}