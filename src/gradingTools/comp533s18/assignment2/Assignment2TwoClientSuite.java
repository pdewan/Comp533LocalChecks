package gradingTools.comp533s18.assignment2;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import gradingTools.comp533s18.assignment3.testcases.StaticArgumentsTestCase;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	OneClientConnection.class,
})
public class Assignment2TwoClientSuite {

public static void twoClientSetupProcesses() {
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Arrays.asList("Server"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Arrays.asList("Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Arrays.asList("Client"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", StaticArgumentsTestCase.TEST_REGISTRY_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArgumentsTestCase.TEST_SERVER_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", StaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", StaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}

}