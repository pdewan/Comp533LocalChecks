package gradingTools.comp533s19.assignment4;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
//import gradingTools.comp533s19.assignment1.testcases;
import gradingTools.comp533s19.assignment1.testcases.TwoClientConnection;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientMessageRatioNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientReadWriteNonAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsAtomic;
import gradingTools.comp533s19.assignment1.testcases.TwoClientThreadsNonAtomic;
import gradingTools.comp533s19.assignment3.Assignment3Suite;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient1Tagged;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveClient2Tagged;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterRegularOutput;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerTagged;
import util.annotations.Comp533Tags;
import util.tags.DistributedTags;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	CounterExplicitReceiveSuite.class,
	CounterCustomRPCSuite.class,
	CounterBlockingRPCSuite.class,
	SimulationBlockingRPCSuite.class
})
	

	
public class Assignment4Suite extends Assignment3Suite {
	public static String[] SIMULATION_CLIENT_TAGS = {DistributedTags.CLIENT, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_SERVER_TAGS = {DistributedTags.SERVER, DistributedTags.GIPC, Comp533Tags.CUSTOM_IPC};
	public static String[] SIMULATION_REGISTRY_TAGS = {DistributedTags.REGISTRY};
//	public static final String[] clientTags = {DistributedTags.NIO, DistributedTags.CLIENT};
//	public static final String[] serverTags = {DistributedTags.NIO, DistributedTags.SERVER};
//	public static final  List<String> clientTagsList = Arrays.asList(clientTags);
//	public static final List<String> serverTagsList = Arrays.asList(serverTags);
//	static int processTimeOut = 45;
//
//	public static int getProcessTimeOut() {
//		return processTimeOut;
//	}
//	public static void setProcessTimeOut(int processTimeOut) {
//		Assignment4Suite.processTimeOut = processTimeOut;
//	}
	public static void main (String[] args) {
		try {
			
			BasicJUnitUtils.interactiveTest(Assignment4Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);

	}
}
