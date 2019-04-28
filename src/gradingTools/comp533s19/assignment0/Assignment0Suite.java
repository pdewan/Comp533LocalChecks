package gradingTools.comp533s19.assignment0;

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
import gradingTools.comp533s19.assignment0.testcases.ConfigurationProvided;
import gradingTools.comp533s19.assignment0.testcases.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.factories.PartitionerFactory;
import gradingTools.comp533s19.assignment0.testcases.objects.PartitionerObject;
import gradingTools.comp533s19.assignment0.testcases.standalone.StandAloneBasicTokenCountResult;
import gradingTools.comp533s19.assignment1.Assignment1Suite;
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
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import util.tags.DistributedTags;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConfigurationProvided.class,
	PartitionerFactory.class,
	PartitionerObject.class,
	StandAloneBasicTokenCountResult.class,
	DistributedTokenCountResult.class
//	Assignment1OneClientSuite.class,
//	Assignment1TwoClientSuite.class,


})
	

	
public class Assignment0Suite extends Assignment1Suite{
//	 static final String[] clientTags = {DistributedTags.NIO, DistributedTags.CLIENT};
//	 static final String[] serverTags = {DistributedTags.NIO, DistributedTags.SERVER};
//	 static final  List<String> clientTagsList = Arrays.asList(clientTags);
//	 static final List<String> serverTagsList = Arrays.asList(serverTags);
	public static final String MAP_REDUCE_PROCESS_TEAM = "MapReduce Team";
	public static final String MAP_REDUCE_SERVER = "MapReduce Server";
	public static final String MAP_REDUCE_CLIENT_1 = "MapReduce Client 1";
	public static final String MAP_REDUCE_CLIENT_2 = "MapReduce Client 2";
//	static int processTimeOut = 45;
//
//	public static int getProcessTimeOut() {
//		return processTimeOut;
//	}
//	public static void setProcessTimeOut(int processTimeOut) {
//		Assignment0Suite.processTimeOut = processTimeOut;
//	}
	public static void main (String[] args) {
		try {
//			setProcessTimeOut(25);

			BasicJUnitUtils.interactiveTest(Assignment0Suite.class);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		BasicProjectIntrospection.setCheckAllSpecifiedTags(true);

	}
}
