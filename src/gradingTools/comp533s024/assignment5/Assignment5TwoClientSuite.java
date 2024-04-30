package gradingTools.comp533s024.assignment5;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gradingTools.comp533s024.assignment5.testcases.RMINIOClientMetaStateBroadcast;
import gradingTools.comp533s024.assignment5.testcases.RMINIOClientMetaStateNoBroadcast;
import gradingTools.comp533s024.assignment5.testcases.RMINIOServerMetaStateBroadcast;
import gradingTools.comp533s024.assignment5.testcases.RMINIOServerMetaStateNoBroadcast;
import gradingTools.comp533s024.assignment5.testcases.RMINIOTwoClientConnection;
import gradingTools.comp533s024.assignment5.testcases.RMINIOTwoClientReadWriteNonAtomic;
//import gradingTools.comp533s20.assignment5.Assignment5TwoClientSuite;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOClientMetaStateBroadcast;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOClientMetaStateNoBroadcast;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOServerMetaStateBroadcast;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOServerMetaStateNoBroadcast;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOTwoClientConnection;
//import gradingTools.comp533s20.assignment6.testcases.GIPCRMINIOTwoClientReadWriteNonAtomic;
import gradingTools.comp533s21.codeReuseHelper.TagsFactory;


@RunWith(Suite.class)
@Suite.SuiteClasses({
//	RMINIOStaticArguments.class,
	RMINIOTwoClientConnection.class,
	RMINIOTwoClientReadWriteNonAtomic.class,
//	RMINIOTwoClientReadWriteAtomic.class,
	RMINIOClientMetaStateNoBroadcast.class,
	RMINIOClientMetaStateBroadcast.class,
	RMINIOServerMetaStateNoBroadcast.class,
	RMINIOServerMetaStateBroadcast.class
	
	
})
public class Assignment5TwoClientSuite {

	public static void twoClientSetupProcesses() {
//		List<String> aClientTags = Arrays.asList(DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
//		List<String> aServerTags = Arrays.asList(DistributedTags.SERVER, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC);
		List<String> aClientTags=TagsFactory.getAssignmentTags().getTwoClientClientTags();
		List<String> aServerTags=TagsFactory.getAssignmentTags().getTwoClientServerTags();
		gradingTools.comp533s20.assignment5.Assignment5TwoClientSuite.twoClientSetupProcesses(aClientTags, aServerTags);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("RegistryBasedDistributedProgram"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("RegistryBasedDistributedProgram", Arrays.asList("Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("RegistryBasedDistributedProgram", Arrays.asList("Registry", "Server", "Client_0", "Client_1"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Registry", Arrays.asList("Registry"));
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", aServerTags);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", aClientTags);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", aClientTags);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Registry", S19FlexibleStaticArgumentsTestCase.TEST_REGISTRY_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", S19FlexibleStaticArgumentsTestCase.TEST_SERVER_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", S19FlexibleStaticArgumentsTestCase.TEST_CLIENT_0_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", S19FlexibleStaticArgumentsTestCase.TEST_CLIENT_1_ARGS);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Registry", 500);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 5000);
//		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	}

}