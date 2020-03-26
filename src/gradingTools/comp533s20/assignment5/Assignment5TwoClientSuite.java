package gradingTools.comp533s20.assignment5;

//import gradingTools.comp533s19.assignment3.Assignment3TwoClientSuite;
import gradingTools.comp533s19.flexible.testcases.FlexibleStaticArgumentsTestCase;
import gradingTools.comp533s20.assignment4.Assignment4TwoClientSuite;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMIClientMetaStateBroadcast;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMIClientMetaStateNoBroadcast;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMIServerMetaStateBroadcast;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMIServerMetaStateNoBroadcast;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMIStaticArguments;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMITwoClientConnection;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMITwoClientReadWriteAtomic;
import gradingTools.comp533s20.assignment5.testcases.GIPCRMITwoClientReadWriteNonAtomic;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.config.BasicExecutionSpecificationSelector;
import util.tags.DistributedTags;


@RunWith(Suite.class)
@Suite.SuiteClasses({
//	GIPCRMIStaticArguments.class,
	GIPCRMITwoClientConnection.class,
	GIPCRMITwoClientReadWriteNonAtomic.class,
//	GIPCRMITwoClientReadWriteAtomic.class,
//	GIPCRMIClientMetaStateNoBroadcast.class,
//	GIPCRMIClientMetaStateBroadcast.class,
//	GIPCRMIServerMetaStateNoBroadcast.class,
//	GIPCRMIServerMetaStateBroadcast.class
	
	
})
public class Assignment5TwoClientSuite {
public static void twoClientSetupProcesses(boolean doGIPC) {
	if (doGIPC) {
		Assignment5TwoClientSuite.twoClientSetupProcesses();
	} else {
		Assignment4TwoClientSuite.twoClientSetupProcesses();
	}
}


public static void twoClientSetupProcesses() {
	List<String> aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.GIPC);
	List<String> aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.GIPC);
	twoClientSetupProcesses(aClientTags, aServerTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", aServerTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", aClientTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", aClientTags);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", FlexibleStaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 5000);
//	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}
public static void twoClientSetupProcesses(List<String> aClientTags, List<String> aServerTags ) {
//	List<String> aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO);
//	List<String> aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_1", "Client_0"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", aServerTags);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", aClientTags);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", aClientTags);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", FlexibleStaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Registry", 500);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Server", 2000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Client_0", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setGraderResourceReleaseTime("Client_1", 5000);
	BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
}

}