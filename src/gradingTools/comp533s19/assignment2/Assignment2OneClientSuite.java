package gradingTools.comp533s19.assignment2;

import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import gradingTools.comp533s19.assignment3.testcases.FlexibleStaticArgumentsTestCase;
import gradingTools.comp533s19.assignment3.testcases.RMINIOOneClientConnection;
import gradingTools.comp533s19.assignment3.testcases.RMINIOOneClientCorrectReadWriteAtomic;
import gradingTools.comp533s19.assignment3.testcases.RMINIOOneClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment3.testcases.RMINIOStaticArguments;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	RMINIOStaticArguments.class,
	RMINIOOneClientConnection.class,
	RMINIOOneClientReadWriteNonAtomic.class,
	RMINIOOneClientCorrectReadWriteAtomic.class


})
public class Assignment2OneClientSuite {
	private static final String DEFAULT_PORT_RMI = ""+Registry.REGISTRY_PORT;



public static void oneClientSetupProcesses() {
	
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}
public static void oneClientSetupProcesses(String[] serverArgs, String[] clientArgs, boolean doRMI) {
	List<String> serverArgList = Arrays.stream(serverArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
	List<String> registryArgList = (serverArgList.size() >= 3 && !serverArgList.get(2).equals(DEFAULT_PORT_RMI)) ? serverArgList.subList(2, 3) : Collections.emptyList();
	List<String> clientArgList = Arrays.stream(clientArgs).filter(s -> !s.isEmpty()).collect(Collectors.toList());
	
//	serverArgList.removeIf(s-> s.isEmpty());
//	clientArgList.removeIf(s-> s.isEmpty());
	
	if (doRMI) {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", registryArgList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
	} else {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client"));
	}
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client", Arrays.asList("Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", serverArgList);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client", clientArgList);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 1000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client", 2000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}
}