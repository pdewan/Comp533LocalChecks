package gradingTools.comp533s19.assignment1;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import grader.basics.execution.BasicExecutionSpecificationSelector;
import grader.basics.junit.BasicJUnitUtils;
import grader.basics.project.BasicProjectIntrospection;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWrite;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientThreadsNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderAtomic;
import gradingTools.comp533s19.assignment1.testcases.ReadWriteUpdateOrderNonAtomic;

import gradingTools.comp533s19.assignment1.testcases.ClientTagged;
import gradingTools.comp533s19.assignment1.testcases.OneClientConnection;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.OneClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.ServerTagged;
import gradingTools.comp533s19.assignment1.testcases.StaticArguments;
//import gradingTools.comp533s19.assignment1.testcases;
import gradingTools.comp533s19.assignment1.testcases.TwoClientConnection;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsNonAtomic;
import util.tags.DistributedTags;


@RunWith(Suite.class)
@Suite.SuiteClasses({

	TwoClientConnection.class,
	// read/write correct and client+server behaviors
	TwoClientReadWriteNonAtomic.class,
	TwoClientReadWriteAtomic.class,
	//  threads exist
	TwoClientThreadsNonAtomic.class,
	TwoClientThreadsAtomic.class,
	TwoClientMessageRatioNonAtomic.class,
	TwoClientMessageRatioAtomic.class,

})
	

	
public class Assignment1TwoClientSuite {

	public static void twoClientSetupProcesses() {
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcessTeams(Arrays.asList("DistributedProgram"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setTerminatingProcesses("DistributedProgram", Arrays.asList("Client_0", "Client_1"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setProcesses("DistributedProgram", Arrays.asList("Server", "Client_0", "Client_1"));
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Server", Assignment1Suite.serverTagsList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_0", Assignment1Suite.clientTagsList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setEntryTags("Client_1", Assignment1Suite.clientTagsList);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Server", StaticArguments.DEFAULT_SERVER_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_0", StaticArguments.DEFAULT_CLIENT_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setArgs("Client_1", StaticArguments.DEFAULT_CLIENT_ARGS);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Server", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_0", 15000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().setSleepTime("Client_1", 2000);
		BasicExecutionSpecificationSelector.getBasicExecutionSpecification().getProcessTeams().forEach(team -> System.out.println("### " + team));
	
	}
	
}
