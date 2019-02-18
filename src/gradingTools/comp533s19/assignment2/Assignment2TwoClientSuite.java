package gradingTools.comp533s19.assignment2;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import gradingTools.comp533s19.assignment3.testcases.ClientMetaStateBroadcast;
import gradingTools.comp533s19.assignment3.testcases.ClientMetaStateNoBroadcast;
import gradingTools.comp533s19.assignment3.testcases.FlexibleStaticArgumentsTestCase;
import gradingTools.comp533s19.assignment3.testcases.RMINIOStaticArguments;
import gradingTools.comp533s19.assignment3.testcases.RMINIOTwoClientCorrectConnection;
import gradingTools.comp533s19.assignment3.testcases.RMINIOTwoClientCorrectReadWriteAtomic;
import gradingTools.comp533s19.assignment3.testcases.RMINIOTwoClientCorrectReadWriteNonAtomic;
import gradingTools.comp533s19.assignment3.testcases.ServerMetaStateBroadcast;
import gradingTools.comp533s19.assignment3.testcases.ServerMetaStateNoBroadcast;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	RMINIOStaticArguments.class,
	RMINIOTwoClientCorrectConnection.class,
	RMINIOTwoClientCorrectReadWriteNonAtomic.class,
	RMINIOTwoClientCorrectReadWriteAtomic.class,
	ClientMetaStateNoBroadcast.class,
	ClientMetaStateBroadcast.class,
	ServerMetaStateNoBroadcast.class,
	ServerMetaStateBroadcast.class,
	
	
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
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", FlexibleStaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}

}